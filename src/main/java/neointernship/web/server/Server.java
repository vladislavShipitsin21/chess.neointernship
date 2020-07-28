package neointernship.web.server;

import neointernship.chess.game.gameplay.activecolorcontroller.ActiveColorController;
import neointernship.chess.game.gameplay.activecolorcontroller.IActiveColorController;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.loop.GameLoop;
import neointernship.chess.game.gameplay.loop.IGameLoop;
import neointernship.chess.game.model.answer.AnswerSimbol;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.ChessType;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.figure.factory.Factory;
import neointernship.chess.game.model.figure.factory.IFactory;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.board.figuresstartposition.FiguresStartPositionRepository;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.game.story.StoryGame;
import neointernship.chess.logger.ErrorLoggerServer;
import neointernship.chess.logger.GameLogger;
import neointernship.web.client.communication.data.endgame.EndGame;
import neointernship.web.client.communication.data.endgame.IEndGame;
import neointernship.web.client.communication.data.initgame.IInitGame;
import neointernship.web.client.communication.data.initgame.InitGame;
import neointernship.web.client.communication.data.initinfo.InitInfoDto;
import neointernship.web.client.communication.data.transformation.TransformationDto;
import neointernship.web.client.communication.data.turn.TurnDto;
import neointernship.web.client.communication.data.update.IUpdate;
import neointernship.web.client.communication.data.update.Update;
import neointernship.web.client.communication.message.*;
import neointernship.web.client.communication.serializer.*;
import neointernship.web.server.connection.ActiveConnectionController;
import neointernship.web.server.connection.UserConnection;

import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
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
    private int lobbyNum = 0;

    public Server() {
        blackSideWaitingConnectionList = new ConcurrentLinkedQueue<>();
        whiteSideWaitingConnectionList = new ConcurrentLinkedQueue<>();

        socketList = new ConcurrentLinkedQueue<>();
        lobbyList = new ConcurrentLinkedQueue<>();
    }

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
                      final int lobbyId, final Server server, final ChessType chessType) {
            this.lobbyId = lobbyId;
            this.server = server;
            final IActiveColorController colorController = new ActiveColorController();
            this.connectionController = new ActiveConnectionController(firstUserConnection, secondUserConnection);

            board = new Board();
            figureFactory = new Factory();
            mediator = new Mediator();
            storyGame = new StoryGame(mediator);
            possibleActionList = new PossibleActionList(board, mediator,storyGame);

            this.chessTypes = chessType;
            figuresStartPositionRepository = new FiguresStartPositionRepository();

            //TODO:
            GameLogger.addLogger(lobbyId);

            gameLoop = new GameLoop(mediator, possibleActionList, board, colorController, storyGame);

            GameLogger.getLogger(lobbyId).logStartGame(firstUserConnection.getName(), secondUserConnection.getName());

            initGameMap();
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
            for (final UserConnection userConnection : connectionController.getConnections()) {
                final BufferedWriter out = userConnection.getOut();
                final IMessage msg = new Message(ClientCodes.INIT_GAME);
                final IInitGame initGame = new InitGame(mediator, board, userConnection.getColor());

                try {
                    send(out, MessageSerializer.serialize(msg));
                    send(out, InitGameSerializer.serialize(initGame));
                } catch (final IOException e) {
                    ErrorLoggerServer.logException(e);
                }
            }
        }

        private void sendUpdatedMediator(final IAnswer answer, final ChessCodes chessCode) {
            for (final UserConnection userConnection : connectionController.getConnections()) {
                final BufferedWriter out = userConnection.getOut();
                final IMessage msg = new Message(ClientCodes.UPDATE);
                final IUpdate update = new Update(answer, chessCode);

                try {
                    send(out, MessageSerializer.serialize(msg));
                    send(out, UpdateSerializer.serialize(update));
                } catch (final IOException e) {
                    ErrorLoggerServer.logException(e);
                }
            }
        }

        private void sendEndGame(final EnumGameState enumGameState) {
            for (final UserConnection userConnection : connectionController.getConnections()) {
                final BufferedWriter out = userConnection.getOut();
                final IMessage msg = new Message(ClientCodes.END_GAME);

                final IEndGame endGame = new EndGame(enumGameState);

                try {
                    send(out, MessageSerializer.serialize(msg));
                    send(out, EndGameSerializer.serialize(endGame));
                } catch (final IOException e) {
                    ErrorLoggerServer.logException(e);
                }
            }
        }

        private IAnswer transformation(final IAnswer answer, final ChessCodes chessCode,
                                       final BufferedReader in, final BufferedWriter out) throws IOException {
            sendUpdatedMediator(answer, chessCode);

            final Message message1 = new Message(ClientCodes.TRANSFORMATION);
            send(out, MessageSerializer.serialize(message1));

            String string = in.readLine();
            TransformationDto transformationDto = TransformationSerializer.deserialize(string);
            char symbol = transformationDto.getFigureChar();

            answer.setSimbol(symbol);
            return new AnswerSimbol(answer.getFinalX(), answer.getFinalY(),
                    answer.getFinalX(), answer.getFinalY(), symbol);

        }

       @Override
        public void run() {
            sendInitInfo();

            while (gameLoop.isAlive()) {
                connectionController.update();
                final UserConnection connection = connectionController.getCurrentConnection();

                ChessCodes chessCode = null;
                IAnswer answer = null;

                do {
                    try {
                        final BufferedWriter out = connection.getOut();
                        final IMessage message = new Message(ClientCodes.TURN);
                        send(out, MessageSerializer.serialize(message));

                        final BufferedReader in = connection.getIn();
                        final String msg = in.readLine();
                        final TurnDto turnDto = AnswerSerializer.deserialize(msg);
                        turnDto.validate();
                        answer = turnDto.getAnswer();

                        chessCode = makeTurn(answer);

                        if (chessCode == ChessCodes.TRANSFORMATION_BEFORE) {
                            answer = transformation(answer, chessCode, in, out);
                            chessCode = makeTurn(answer);
                        }

                        GameLogger.getLogger(lobbyId).logPlayerMoveAction(connection.getColor(),
                                mediator.getFigure(new Field(answer.getFinalX(), answer.getFinalY())),
                                new Field(answer.getStartX(), answer.getStartY()),
                                new Field(answer.getFinalX(), answer.getFinalY()), chessCode);
                    } catch (final Exception e) {
                        ErrorLoggerServer.logException(e);
                    }

                } while(chessCode == ChessCodes.ERROR);
                sendUpdatedMediator(answer, chessCode);
            }

            gameLoop.getMatchResult();

            final EnumGameState enumGameState = gameLoop.getMatchResult().getValue();

            sendEndGame(enumGameState);
            GameLogger.getLogger(lobbyId).logEndGame(enumGameState);

            downService();
        }

        public ChessCodes makeTurn(final IAnswer answer) {
            return gameLoop.doIteration(answer);
        }

        /**
         * закрытие сервера, удаление себя из списка нитей
         */
        private void downService() {
            for (int i = 0; i < 2; i++) {
                connectionController.update();
                try {
                    final UserConnection currentConnection = connectionController.getCurrentConnection();
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

    private ClientCodes checkPlayers(final UserConnection userConnection) throws Exception {
        final Message messageHandShake = new Message(ClientCodes.HAND_SHAKE);
        final String msg = MessageSerializer.serialize(messageHandShake);

        final String answer;
        final MessageDto message;

        send(userConnection.getOut(), msg);

        answer = userConnection.getIn().readLine();
        message = MessageSerializer.deserialize(answer);
        message.validate();

        return message.getClientCodes();
    }

    private void createNewLobby() throws Exception {
        if (whiteSideWaitingConnectionList.size() > 0 && blackSideWaitingConnectionList.size() > 0) {
            UserConnection whiteSideConnection;
            UserConnection blackSideConnection;
            ClientCodes clientCodes;

            do {
                whiteSideConnection = whiteSideWaitingConnectionList.poll();
                clientCodes = checkPlayers(whiteSideConnection);
            }while (whiteSideWaitingConnectionList.size() > 0 && clientCodes != ClientCodes.YES);
            if (clientCodes == ClientCodes.NO) return;

            do {
                blackSideConnection = blackSideWaitingConnectionList.poll();
                clientCodes = checkPlayers(blackSideConnection);
            }while (blackSideWaitingConnectionList.size() > 0 && clientCodes != ClientCodes.YES);

            if (clientCodes == ClientCodes.NO) {
                whiteSideWaitingConnectionList.add(whiteSideConnection);
                return;
            }

            final Lobby lobby = new Lobby(whiteSideConnection, blackSideConnection, lobbyNum++, this, ChessType.CLASSIC);
            lobbyList.add(lobby);
            lobby.start();
        }
    }

    private void createNewConnection() throws IOException {
        final Socket socket = socketList.poll();

        assert socket != null;
        final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        final String name;
        final Color color;

        /*
        TODO с Андреем: принятие цвета,
         приведение цвета к Color
         */
        try {
            final IMessage message = new Message(ClientCodes.INIT_INFO);
            send(out, MessageSerializer.serialize(message));

            final String msg = in.readLine();
            final InitInfoDto info = InfoSerializer.deserialize(msg);
            info.validate();

            color = info.getColor();
            name = info.getName();

            final UserConnection connection = new UserConnection(in, out, socket, color, name);

            if (connection.getColor() == Color.WHITE) {
                whiteSideWaitingConnectionList.add(connection);
            } else {
                blackSideWaitingConnectionList.add(connection);
            }

        } catch (final Exception e) {
            ErrorLoggerServer.logException(e);
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void startServer() throws IOException {
        System.out.println(String.format("Server started, port: %d", PORT));

        while (true) {
            try (final ServerSocket serverSocket = new ServerSocket(PORT)) {
                while (true) {
                    final Socket socket = serverSocket.accept();
                    socketList.add(socket);

                    createNewConnection();
                    createNewLobby();
                }
            } catch (final BindException e) {
                ErrorLoggerServer.logException(e);
            } catch (final Exception exception) {
                exception.printStackTrace();
            }
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
