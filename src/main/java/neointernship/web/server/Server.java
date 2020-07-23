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
import neointernship.chess.game.model.figure.factory.Factory;
import neointernship.chess.game.model.figure.factory.IFactory;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.board.figuresstartposition.FiguresStartPositionRepository;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.game.story.StoryGame;
import neointernship.chess.logger.GameLogger;
import neointernship.chess.logger.IGameLogger;
import neointernship.web.client.communication.data.info.InfoDto;
import neointernship.web.client.communication.data.initgame.IInitGame;
import neointernship.web.client.communication.data.initgame.InitGame;
import neointernship.web.client.communication.data.turn.TurnDto;
import neointernship.web.client.communication.data.update.IUpdate;
import neointernship.web.client.communication.data.update.Update;
import neointernship.web.client.communication.message.IMessage;
import neointernship.web.client.communication.message.Message;
import neointernship.web.client.communication.message.ClientCodes;
import neointernship.web.client.communication.message.ChessCodes;
import neointernship.web.client.communication.serializer.*;
import neointernship.web.server.connection.ActiveConnectionController;
import neointernship.web.server.connection.UserConnection;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Консольный многопользовательский чат.
 * Сервер
 */
public class Server {
    static final int PORT = 8081;
    static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");
    private static final int COUNT_EVENTS_IN_HISTORY = 20;

    private final Queue<UserConnection> blackSideWaitingConnectionList;
    private final Queue<UserConnection> whiteSideWaitingConnectionList;

    private final Queue<Socket> socketList;
    private final Queue<Lobby> lobbyList;

    public Server() {
        blackSideWaitingConnectionList = new ConcurrentLinkedQueue<>();
        whiteSideWaitingConnectionList = new ConcurrentLinkedQueue<>();

        socketList = new ConcurrentLinkedQueue<>();
        lobbyList = new ConcurrentLinkedQueue<>();
    }
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

            gameLoop = new GameLoop(mediator, possibleActionList, board, colorController, gameLogger, storyGame);

            gameLogger.logStartGame(firstUserConnection.getName(), secondUserConnection.getName());

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

        private void sendInitInfo() {
            for (UserConnection userConnection : connectionController.getConnections()) {
                BufferedWriter out = userConnection.getOut();
                IMessage msg = new Message(ClientCodes.INIT_INFO);
                IInitGame initGame = new InitGame(mediator, board, userConnection.getColor());

                try {
                    send(out, MessageSerializer.serialize(msg));
                    send(out, InitGameSerializer.serialize(initGame));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void sendUpdatedMediator() {
            for (UserConnection userConnection : connectionController.getConnections()) {
                BufferedWriter out = userConnection.getOut();
                IMessage msg = new Message(ClientCodes.UPDATE);
                IUpdate update = new Update(mediator);

                try {
                    send(out, MessageSerializer.serialize(msg));
                    send(out, UpdateSerializer.serialize(update));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


       @Override
        public void run() {
            sendInitInfo();

            while (gameLoop.isAlive()) {
                sendUpdatedMediator();
                connectionController.update();
                UserConnection connection = connectionController.getCurrentConnection();

                try {
                    IAnswer answer = null;
                    do {
                        BufferedWriter out = connection.getOut();
                        IMessage message = new Message(ClientCodes.TURN);
                        send(out, MessageSerializer.serialize(message));

                        BufferedReader in = connection.getIn();
                        String msg = in.readLine();
                        TurnDto turnDto = AnswerSerializer.deserializer(msg);
                        turnDto.validate();
                        answer = turnDto.getAnswer();

                    } while(makeTurn(answer) != ChessCodes.OK);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            gameLoop.getMatchResult();
            downService();
        }

        public ChessCodes makeTurn(final IAnswer answer) {
            if (gameLoop.doIteration(answer)) {
                return ChessCodes.OK;
            }

            return ChessCodes.ERROR;
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

    private void createNewLobby() throws IOException {
        if (whiteSideWaitingConnectionList.size() > 0 && blackSideWaitingConnectionList.size() > 0) {
            final UserConnection whiteSideConnection = whiteSideWaitingConnectionList.poll();
            final UserConnection blackSideConnection = blackSideWaitingConnectionList.poll();

            Lobby lobby = new Lobby(whiteSideConnection, blackSideConnection, lobbyList.size(), this, ChessType.CLASSIC);
            lobbyList.add(lobby);
            lobby.start();
        }
    }

    private void createNewConnection() throws IOException {
        final Socket socket = socketList.poll();

        assert socket != null;
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String name = "";
        Color color = null;

        /*
        TODO с Андреем: принятие цвета,
         приведение цвета к Color
         */
        try {
            IMessage message = new Message(ClientCodes.INIT_INFO);
            send(out, MessageSerializer.serialize(message));

            String msg = in.readLine();
            InfoDto info = InfoSerializer.deserialize(msg);
            info.validate();

            color = info.getColor();
            name = info.getName();

        } catch (Exception e) {
            e.printStackTrace();
        }
        UserConnection connection = new UserConnection(in, out, socket, color, name);

        if (connection.getColor() == Color.WHITE) {
            whiteSideWaitingConnectionList.add(connection);
        } else {
            blackSideWaitingConnectionList.add(connection);
        }
    }


    @SuppressWarnings("InfiniteLoopStatement")
    private void startServer() throws IOException {
        System.out.println(String.format("Server started, port: %d", PORT));

        try (final ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                final Socket socket = serverSocket.accept();
                socketList.add(socket);

                createNewConnection();
                createNewLobby();
            }
        } catch (final BindException e) {
            e.printStackTrace();
        }
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

    public static void main(final String[] args) throws IOException {
        final Server server = new Server();
        server.startServer();
    }
}
