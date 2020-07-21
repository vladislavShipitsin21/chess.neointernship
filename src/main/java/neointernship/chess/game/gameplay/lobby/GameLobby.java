package neointernship.chess.game.gameplay.lobby;

import neointernship.chess.client.communication.data.initgame.IInitGame;
import neointernship.chess.client.communication.data.initgame.InitGame;
import neointernship.chess.client.communication.message.Message;
import neointernship.chess.client.communication.message.MessageCode;
import neointernship.chess.client.communication.serializer.SerializerForInitGame;
import neointernship.chess.client.communication.serializer.SerializerForMessage;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.loop.GameLoop;
import neointernship.chess.game.gameplay.loop.IGameLoop;
import neointernship.chess.game.model.enums.ChessType;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.factory.Factory;
import neointernship.chess.game.model.figure.factory.IFactory;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.board.figuresstartposition.FiguresStartPositionRepository;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.logger.IGameLogger;

import java.io.IOException;

import static neointernship.chess.Main.*;


public class GameLobby implements ILobby {
    private final IBoard board;
    private final IFactory figureFactory;
    private final Mediator mediator;
    private final IPossibleActionList possibleActionList;

    private final ChessType chessTypes;
    private final FiguresStartPositionRepository figuresStartPositionRepository;
    private final Character emptyFieldChar = '.';

    private final IGameLoop gameLoop;

    public GameLobby(final IPlayer firstPlayer, final IPlayer secondPlayer,
                     final ChessType chessType, final IGameLogger gameLogger) {
        board = new Board();
        figureFactory = new Factory();
        mediator = new Mediator();
        possibleActionList = new PossibleActionList(board, mediator); // todo создание с пустым mediator

        this.chessTypes = chessType;
        figuresStartPositionRepository = new FiguresStartPositionRepository();

        gameLoop = new GameLoop(mediator, possibleActionList, board, firstPlayer, secondPlayer, gameLogger);

        gameLogger.logStartGame(firstPlayer, secondPlayer);

        initializeLobby();
    }

    private void initializeLobby() {
        final Character[][] figuresRepository = figuresStartPositionRepository.getStartPosition(chessTypes);

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                final IField field = board.getField(i, j);

                final Character currentChar = figuresRepository[i][j];
                if (currentChar != emptyFieldChar) {
                        final Color color = i > 4 ? Color.WHITE : Color.BLACK;
                    final Figure figure = figureFactory.createFigure(currentChar, color);

                    mediator.addNewConnection(field, figure);
                }
            }
        }

        possibleActionList.updateRealLists();
        start();
    }

    @Override
    public void start() {
        Message message1 = new Message(MessageCode.INIT_GAME);
        IInitGame initGame1 = new InitGame(mediator, board, Color.WHITE);
        Message message2 = new Message(MessageCode.INIT_GAME);
        IInitGame initGame2 = new InitGame(mediator, board, Color.BLACK);
        try {
            String s1 = SerializerForMessage.serializer(message1);
            String s = SerializerForInitGame.serializer(initGame1);
            System.out.println(s1);
            System.out.println(s);
            out1.write(SerializerForMessage.serializer(message1) + "\n");
            out1.flush();
            out1.write(SerializerForInitGame.serializer(initGame1) + "\n");
            out1.flush();
            System.out.println(in1.readLine());

            out2.write(SerializerForMessage.serializer(message2) + "\n");
            out2.flush();
            in2.readLine();
            out2.write("1");
            out2.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameLoop.activate();
    }
}
