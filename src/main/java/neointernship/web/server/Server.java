package neointernship.web.server;

import neointernship.chess.game.gameplay.activecolorcontroller.ActiveColorController;
import neointernship.chess.game.gameplay.activecolorcontroller.IActiveColorController;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.loop.GameLoop;
import neointernship.chess.game.gameplay.loop.IGameLoop;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.ChessType;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.ServerCodes;
import neointernship.chess.game.model.figure.factory.Factory;
import neointernship.chess.game.model.figure.factory.IFactory;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.player.Player;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.board.figuresstartposition.FiguresStartPositionRepository;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.game.story.StoryGame;
import neointernship.chess.logger.GameLogger;
import neointernship.chess.logger.IGameLogger;
import neointernship.web.server.connection.ActiveConnectionController;
import neointernship.web.server.connection.UserConnection;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Консольный многопользовательский чат.
 * Сервер
 */
public class Server {

    static final int PORT = 8081;
    static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");
    private static final int COUNT_EVENTS_IN_HISTORY = 20;


    private final ConcurrentLinkedQueue<Socket> socketList = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<Lobby> lobbyList = new ConcurrentLinkedQueue<>();
  //  private History history = new History(); // история

    private class Lobby extends Thread {
        private final IBoard board;
        private final IFactory figureFactory;
        private final IMediator mediator;
        private final IPossibleActionList possibleActionList;
        private final IStoryGame storyGame;

        private final int lobbyId;
        private final ChessType chessTypes;
        private final FiguresStartPositionRepository figuresStartPositionRepository;
        private final Character FIELD_CHAR_EMPTY = '.';

        private final IGameLoop gameLoop;

        private final Server server;
        private final ActiveConnectionController connectionController;

        private Lobby(final UserConnection firstUserConnection, final UserConnection secondUserConnection,
                      final int lobbyId, final Server server, final ChessType chessType) throws IOException {
            this.lobbyId = lobbyId;
            this.server = server;
            IActiveColorController colorController = new ActiveColorController();
            this.connectionController = new ActiveConnectionController(firstUserConnection, secondUserConnection);


            board = new Board();
            figureFactory = new Factory();
            mediator = new Mediator();
            storyGame = new StoryGame(mediator);
            possibleActionList = new PossibleActionList(board, mediator,storyGame);

            this.chessTypes = chessType;
            figuresStartPositionRepository = new FiguresStartPositionRepository();

            IGameLogger gameLogger = new GameLogger(lobbyId);

            gameLoop = new GameLoop(mediator, possibleActionList, board, colorController, gameLogger,storyGame);

            gameLogger.logStartGame(firstUserConnection.getPlayer(), secondUserConnection.getPlayer());

            initGameMap();
            start();
        }

        private void initGameMap() {
            final Character[][] figuresRepository = figuresStartPositionRepository.getStartPosition(chessTypes);
            for (int i = 0; i < board.getSize(); i++) {
                for (int j = 0; j < board.getSize(); j++) {
                    final IField field = board.getField(i, j);

                    final Character currentChar = figuresRepository[i][j];
                    if (currentChar != FIELD_CHAR_EMPTY) {
                        final Color color = i > 4 ? Color.WHITE : Color.BLACK;
                        final Figure figure = figureFactory.createFigure(currentChar, color);

                        mediator.addNewConnection(field, figure);
                    }
                }
            }
            possibleActionList.updateRealLists();
        }

       @Override
        public void run() {
            connectionController.update();
            final UserConnection currentConnection = connectionController.getCurrentConnection();

            while (gameLoop.isAlive()) {
                try {
                    send(currentConnection.getOut(), String.valueOf(ServerCodes.TURN));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //TODO принятие и сериализация ответа
                /*final IAnswer currentAnswer = currentPlayer.getAnswer(board, mediator, possibleActionList);
                makeTurn(currentAnswer);
                 */
            }
            gameLoop.getMatchResult();
            downService();
        }

        public ServerCodes makeTurn(final IAnswer answer) {
            gameLoop.doIteration(answer);
            return ServerCodes.BAD_REQUEST;
        }


        /**
         * отсылка одного сообщения клиенту
         *
         * @param msg сообщение
         */
        private void send(final BufferedWriter out, final String msg) throws IOException {
            out.write(msg + "\n");
            out.flush();
        }

        /**
         * закрытие сервера, удаление себя из списка нитей
         */
        private void downService() {
            for (int i = 0; i < 2; i++) {
                connectionController.update();
                try {
                    UserConnection currentConnection = connectionController.getCurrentConnection();
                    if (!currentConnection.getSocket().isClosed()) {
                        currentConnection.getSocket().close();
                        currentConnection.getOut().close();
                        currentConnection.getIn().close();
                        server.lobbyList.remove(this);
                    }
                } catch (final IOException ignored) {
                }
            }
        }
    }

    private void initializeNewLobby() throws IOException {
        final Socket firstPlayerSocket = socketList.poll();
        final Socket secondPlayerSocket = socketList.poll();

        assert firstPlayerSocket != null;
        BufferedReader in = new BufferedReader(new InputStreamReader(firstPlayerSocket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(firstPlayerSocket.getOutputStream()));
        String firstPlayerName = "";
        try {
            out.write(String.valueOf(ServerCodes.INIT));
            firstPlayerName = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        UserConnection firstConnection = new UserConnection(in,
                out,
                new Player(firstPlayerName, Color.WHITE),
                firstPlayerSocket);

        assert secondPlayerSocket != null;
        String secondPlayerName = "";
        in = new BufferedReader(new InputStreamReader(secondPlayerSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(secondPlayerSocket.getOutputStream()));
        try {
            out.write(String.valueOf(ServerCodes.INIT));
            secondPlayerName = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        UserConnection secondConnection = new UserConnection(in,
                out,
                new Player(secondPlayerName, Color.BLACK),
                secondPlayerSocket);

        int lobbyID = lobbyList.size() + 1;
        Lobby lobby = new Lobby(firstConnection, secondConnection, lobbyID, this, ChessType.CLASSIC);
        lobbyList.add(lobby);
        lobby.start();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void startServer() throws IOException {
        System.out.println(String.format("Server started, port: %d", PORT));
        try (final ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                final Socket socket = serverSocket.accept();
                socketList.add(socket);

                if (socketList.size() >= 2) {
                    initializeNewLobby();
                }
            }
        } catch (final BindException e) {
            e.printStackTrace();
        }
    }

    public static void main(final String[] args) throws IOException {
        final Server server = new Server();
        server.startServer();
    }
}
