package neointernship.chess.game.selfplay;

import neointernship.chess.game.console.ConsoleBoardWriter;
import neointernship.chess.game.console.IConsoleBoardWriter;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.gameprocesscontroller.GameProcessController;
import neointernship.chess.game.gameplay.gameprocesscontroller.IGameProcessController;
import neointernship.chess.game.gameplay.gamestate.controller.GameStateController;
import neointernship.chess.game.gameplay.gamestate.controller.IGameStateController;
import neointernship.chess.game.gameplay.gamestate.controller.draw.DrawController;
import neointernship.chess.game.gameplay.kingstate.controller.IKingStateController;
import neointernship.chess.game.gameplay.kingstate.controller.KingsStateController;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.model.subscriber.ISubscriber;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.logger.IGameLogger;

public class GameLoopLocal {
    private final IMediator mediator;
    private final IPossibleActionList possibleActionList;
    private final IBoard board;
    private IPlayer activePlayer;

    private final ActivePlayerController activePlayerController;
    private final IGameStateController gameStateController;
    private final IGameProcessController gameProcessController;
    private final IKingStateController kingStateController;

    private final IConsoleBoardWriter consoleBoardWriter;

    private final DrawController drawController;

    private final IGameLogger gameLogger;


    public GameLoopLocal(final IMediator mediator,
                    final IPossibleActionList possibleActionList,
                    final IBoard board,
                    final IPlayer firstPlayer,
                    final IPlayer secondPlayer,
                    final IGameLogger gameLogger,
                    final IStoryGame storyGame) {
        this.activePlayer = firstPlayer;

        this.mediator = mediator;
        this.possibleActionList = possibleActionList;
        this.board = board;
        this.gameLogger = gameLogger;

        activePlayerController = new ActivePlayerController(firstPlayer, secondPlayer);
        gameStateController = new GameStateController(possibleActionList, mediator, gameLogger);
        gameProcessController = new GameProcessController(mediator, possibleActionList, board,gameLogger,storyGame);
        kingStateController = new KingsStateController(possibleActionList, mediator, activePlayer.getColor());

        consoleBoardWriter = new ConsoleBoardWriter(mediator, board);

        drawController = new DrawController(mediator, gameLogger,storyGame);

    }

    /**
     * Активация главного игрового цикла.
     */
    public void activate() {
        kingStateController.addToSubscriber((ISubscriber) gameStateController);

        do {
            showAvailableMoves();
            consoleBoardWriter.printBoard();

            do {
                IAnswer answer = activePlayer.getAnswer(mediator, possibleActionList);
                gameProcessController.makeTurn(activePlayer.getColor(), answer,gameLogger);

            } while (!gameProcessController.playerDidMove());

            activePlayer = activePlayerController.getNextPlayer();
            kingStateController.setActiveColor(activePlayer.getColor());
            kingStateController.updateState();

        }while (gameStateController.isMatchAlive() && !drawController.isDraw());

        consoleBoardWriter.printBoard();
        gameStateController.showResults();
    }

    private void showAvailableMoves() {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                IField field = board.getField(i, j);
                Figure figure = mediator.getFigure(field);
                if (figure != null) {
                    System.out.println(figure.getName() + " " + figure.getColor());
                    for (IField field1 : possibleActionList.getRealList(figure)) {
//                        System.out.print("(" + field1.getXCoord() + ";" + field1.getYCoord() + ")" + " ");
                        System.out.print(field1.showField() + " ");
                    }
                    System.out.print("\n");
                }
            }
        }
    }
}
