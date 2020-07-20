package neointernship.chess.web.server;

import neointernship.chess.game.gameplay.activeplayercontroller.ActivePlayerController;
import neointernship.chess.game.gameplay.activeplayercontroller.IActivePlayerController;
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
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.player.Player;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.board.figuresstartposition.FiguresStartPositionRepository;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.logger.IGameLogger;

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

    enum Command {
        WARNING("warning"),
        STOP_CLIENT_FROM_SERVER("stop client from server"),
        STOP_CLIENT("stop client"),
        STOP_ALL_CLIENTS("stop all clients"),
        STOP_SERVER("stop server"),
        ;

        private final String commandName;

        Command(final String commandName) {
            this.commandName = commandName;
        }

        boolean equalCommand(final String message) {
            return commandName.equals(message);
        }

        static boolean isCommandMessage(final String message) {
            for (final Command command : values()) {
                if (command.equalCommand(message)) {
                    return true;
                }
            }
            return false;
        }
    }

    private final ConcurrentLinkedQueue<Socket> socketList = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<Lobby> lobbyList = new ConcurrentLinkedQueue<>();
  //  private History history = new History(); // история

    private class Lobby extends Thread {
        private final IPlayer firstPlayer;
        private final IPlayer secondPlayer;

        private final IBoard board;
        private final IFactory figureFactory;
        private final IMediator mediator;
        private final IPossibleActionList possibleActionList;

        private final ChessType chessTypes;
        private final FiguresStartPositionRepository figuresStartPositionRepository;
        private final Character FIELD_CHAR_EMPTY = '.';

        private final IGameLoop gameLoop;

        private final Server server;
        private final IActivePlayerController playerController;

        private Lobby(final Socket firstPlayerSocket, final Socket secondPlayerSocket,
                      final int lobbyId, final Server server, final ChessType chessType) throws IOException {
            this.firstPlayer = new Player(Color.WHITE, firstPlayerSocket);
            this.secondPlayer = new Player(Color.BLACK, secondPlayerSocket);


            this.server = server;
            this.playerController = new ActivePlayerController(firstPlayer, secondPlayer);

            board = new Board();
            figureFactory = new Factory();
            mediator = new Mediator();
            possibleActionList = new PossibleActionList(board, mediator);

            this.chessTypes = chessType;
            figuresStartPositionRepository = new FiguresStartPositionRepository();

            //TODO:
            IGameLogger gameLogger = null;

            gameLoop = new GameLoop(mediator, possibleActionList, board, firstPlayer, secondPlayer, gameLogger);

            // TODO: gameLogger.logStartGame(firstPlayer, secondPlayer);

            initGameMap();
            getPlayersNames();
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

        private void getPlayersNames() {
            for (int i = 0; i < 2; i++) {
                playerController.update();
                IPlayer currentPlayer = playerController.getCurrentPlayer();
                BufferedWriter out = currentPlayer.getWriter();
                BufferedReader in = currentPlayer.getReader();

                try {
                    out.write(String.valueOf(ServerCodes.INIT));
                    String name = in.readLine();
                    currentPlayer.setName(name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void run() {
            playerController.update();
            final IPlayer currentPlayer = playerController.getCurrentPlayer();

            while (gameLoop.isAlive()) {
                try {
                    send(currentPlayer.getWriter(), String.valueOf(ServerCodes.TURN));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //TODO принятие и сериализация ответа
                //final IAnswer currentAnswer = currentPlayer.getAnswer(board, mediator, possibleActionList);
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
                playerController.update();
                try {
                    IPlayer player = playerController.getCurrentPlayer();
                    if (!player.getSocket().isClosed()) {
                        player.getSocket().close();
                        player.getWriter().close();
                        player.getReader().close();
                        server.lobbyList.remove(this);
                    }
                } catch (final IOException ignored) {
                }
            }
        }
    }

    private void initializeNewGame() throws IOException {
        final Socket firstPlayerSocket = socketList.poll();
        final Socket secondPlayerSocket = socketList.poll();

        int lobbyID = lobbyList.size() + 1;
        Lobby lobby = new Lobby(firstPlayerSocket, secondPlayerSocket, lobbyID, this, ChessType.CLASSIC);
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
                    initializeNewGame();
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
