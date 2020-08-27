package neointernship.selfplay;

import neointernship.chess.game.gameplay.activecolorcontroller.ActiveColorController;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.gameplay.loop.GameLoop;
import neointernship.chess.game.gameplay.loop.IGameLoop;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.ChessType;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumMatchResult;
import neointernship.chess.game.model.figure.factory.Factory;
import neointernship.chess.game.model.figure.factory.IFactory;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.player.Bot;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.board.figuresstartposition.FiguresStartPositionRepository;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.game.story.StoryGame;
import neointernship.chess.logger.GameLogger;
import neointernship.chess.logger.IGameLogger;
import neointernship.chess.statistics.Statistics;
import neointernship.web.client.communication.message.TurnStatus;

public class LocalLobby {

    private final IBoard board;
    private final IFactory figureFactory;
    private final IMediator mediator;
    private final IPossibleActionList possibleActionList;
    private final IStoryGame storyGame;

    private final FiguresStartPositionRepository figuresStartPositionRepository;
    private final ActiveColorController activeColorController;
    private final Character FIELD_CHAR_EMPTY = '.';

    private final IGameLoop gameLoop;
    private final Bot player1;
    private final Bot player2;

    private final IGameLogger gameLogger;
    private final int lobbyId;

    public LocalLobby(final Bot player1, final Bot player2, final int lobbyId) {
        this.player1 = player1.getColor() == Color.WHITE ? player1 : player2;
        this.player2 = player2.getColor() == Color.BLACK ? player2 : player1;

        board = new Board();
        figureFactory = new Factory();
        mediator = new Mediator();
        storyGame = new StoryGame(mediator);
        possibleActionList = new PossibleActionList(board, mediator, storyGame);

        figuresStartPositionRepository = new FiguresStartPositionRepository();
        activeColorController = new ActiveColorController();

        gameLoop = new GameLoop(mediator, possibleActionList, board, activeColorController, storyGame);

        GameLogger.addLogger(lobbyId);
        gameLogger = GameLogger.getLogger(lobbyId);
        this.lobbyId = lobbyId;

        initGameMap();
    }

    private void initGameMap() {
        final Character[][] figuresRepository = figuresStartPositionRepository.getStartPosition(ChessType.CLASSIC);
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

    public void run() {
        System.out.println("start lobby : " + lobbyId);

        gameLogger.logStartGame(player1.getName(), player2.getName());
        Color activeColor = activeColorController.getCurrentColor();
        IPlayer activePlayer = player1;

        while (gameLoop.isAlive()){
            activeColorController.update();
            activeColor = activeColorController.getCurrentColor();
            activePlayer = player1.getColor() == activeColor ? player1 : player2;
            TurnStatus turnStatus;
            IAnswer answer;

            do {
                answer = activePlayer.getAnswer(mediator, possibleActionList);
                turnStatus = gameLoop.doIteration(answer);
            } while (turnStatus == TurnStatus.ERROR);


            gameLogger.logPlayerMoveAction(activeColor,
                    mediator.getFigure(board.getField(answer.getFinalX(), answer.getFinalY())),
                    board.getField(answer.getStartX(), answer.getStartY()),
                    board.getField(answer.getFinalX(), answer.getFinalY()), turnStatus);
        }

        final IGameState gameState = gameLoop.getMatchResult();

        gameLogger.logEndGame(gameState.getValue());

        setStatistic(activePlayer,gameState);

        System.out.println("finish lobby : " + lobbyId);
        player1.printInfoTime();
        player2.printInfoTime();
    }
    private void setStatistic(IPlayer activePlayer, final IGameState gameState){
        final EnumMatchResult matchResult = EnumMatchResult.getEnumMatchResult(gameState.getValue());

        final String nameFirstPlayer = activePlayer.getName();
        final Color colorFirstPlayer = activePlayer.getColor();

        activeColorController.update();
        final Color activeColor = activeColorController.getCurrentColor();
        activePlayer = player1.getColor() == activeColor ? player1 : player2;


        final String nameSecondPlayer = activePlayer.getName();
        final Color colorSecondPlayer = activePlayer.getColor();

        try {
            Statistics.setStatistics(nameFirstPlayer,colorFirstPlayer,matchResult,
                    nameSecondPlayer,colorSecondPlayer, EnumMatchResult.swapEnumMatchResult(matchResult));
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
