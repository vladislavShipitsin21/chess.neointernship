package neointernship.chess.game.gameplay.lobby;

import neointernship.chess.game.gameplay.loop.GameLoop;
import neointernship.chess.game.gameplay.loop.IGameLoop;
import neointernship.chess.game.model.enums.ChessTypes;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.Figure;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.figure.actions.PossibleActionList;
import neointernship.chess.game.model.figure.factory.Factory;
import neointernship.chess.game.model.figure.factory.IFactory;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.board.figuresstartposition.FiguresStartPositionRepository;
import neointernship.chess.game.model.playmap.field.IField;

public class GameLobby implements ILobby {
    private final IBoard board;
    private final IFactory figureFactory;
    private final IMediator mediator;
    private final IPossibleActionList possibleActionList;

    private final IPlayer firstPlayer;
    private final IPlayer secondPlayer;

    private final ChessTypes chessTypes;
    private final FiguresStartPositionRepository figuresStartPositionRepository;

    private final IGameLoop gameLoop;


    public GameLobby(final IPlayer firstPlayer, final IPlayer secondPlayer, final ChessTypes chessType) {
        board = new Board();
        figureFactory = new Factory();
        mediator = new Mediator();
        possibleActionList = new PossibleActionList();

        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;

        this.chessTypes = chessType;
        figuresStartPositionRepository = new FiguresStartPositionRepository();

        gameLoop = new GameLoop();

        initializeLobby();
    }

    private void initializeLobby() {
        Character[][] figuresRepository = figuresStartPositionRepository.getStartPosition(chessTypes);

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); i++) {
                IField field = board.getField(i, j);

                final Character currentChar = figuresRepository[i][j];
                Color color = i > 4 ? Color.WHITE : Color.BLACK;
                Figure figure = figureFactory.createNewFigure(currentChar, color);

                mediator.addNewConnection(field, figure);
            }
        }

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                IField field = board.getField(i, j);
                Figure figure = mediator.getFigure(field);

                possibleActionList.updateLists(figure, board);
            }
        }

        start();
    }

    @Override
    public void start() {
        gameLoop.activate(mediator,
                possibleActionList,
                board,
                firstPlayer,
                secondPlayer);
    }
}
