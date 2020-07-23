package neointernship.chess.game.selfplay;

import neointernship.chess.game.gameplay.activecolorcontroller.ActiveColorController;
import neointernship.chess.game.gameplay.activecolorcontroller.IActiveColorController;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.loop.GameLoop;
import neointernship.chess.game.gameplay.loop.IGameLoop;
import neointernship.chess.game.model.enums.ChessType;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.factory.*;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.*;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.player.RandomBot;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.board.figuresstartposition.FiguresStartPositionRepository;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.game.story.StoryGame;
import neointernship.chess.logger.IGameLogger;

public class GameLobby {
    private final IBoard board;
    private final IFactory figureFactory;
    private final IMediator mediator;
    private final IPossibleActionList possibleActionList;

    private final ChessType chessTypes;
    private final FiguresStartPositionRepository figuresStartPositionRepository;
    private final Character emptyFieldChar = '.';

    private final GameLoopLocal gameLoop;

    private final IStoryGame storyGame;



    public GameLobby(final IPlayer firstPlayer, final IPlayer secondPlayer,
                     final ChessType chessType, final IGameLogger gameLogger) {
        board = new Board();
        figureFactory = new Factory();
        mediator = new Mediator();
        this.storyGame = new StoryGame(mediator);
        possibleActionList = new PossibleActionList(board, mediator,storyGame);

        this.chessTypes = chessType;
        figuresStartPositionRepository = new FiguresStartPositionRepository();

        IActiveColorController colorController = new ActiveColorController();

        gameLoop = new GameLoopLocal(mediator, possibleActionList, board, firstPlayer,secondPlayer, gameLogger,storyGame);

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
    }

    public void start() {
        gameLoop.activate();
    }
}
