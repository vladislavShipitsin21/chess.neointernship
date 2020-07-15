package neointernship.chess.game.gameplay.loop;

import neointernship.chess.game.console.ConsoleBoardWriter;
import neointernship.chess.game.console.IConsoleBoardWriter;
import neointernship.chess.game.gameplay.activeplayercontroller.ActivePlayerController;
import neointernship.chess.game.gameplay.activeplayercontroller.IActivePlayerController;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.gameprocesscontroller.GameProcessController;
import neointernship.chess.game.gameplay.gameprocesscontroller.IGameProcessController;
import neointernship.chess.game.gameplay.gamestate.controller.GameStateController;
import neointernship.chess.game.gameplay.gamestate.controller.IGameStateController;
import neointernship.chess.game.gameplay.kingstate.controller.IKingStateController;
import neointernship.chess.game.gameplay.kingstate.controller.KingsStateController;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.model.subscriber.ISubscriber;
import neointernship.chess.logger.IGameLogger;

/**
 * Класс, реализующий основное ядро игры (игровой цикл)
 */
public class GameLoop implements IGameLoop {
    private final IMediator mediator;
    private final IPossibleActionList possibleActionList;
    private final IBoard board;
    private IPlayer activePlayer;

    private final IActivePlayerController activePlayerController;
    private final IGameStateController gameStateController;
    private final IGameProcessController gameProcessController;
    private final IKingStateController kingStateController;

    private final IConsoleBoardWriter consoleBoardWriter;

    private final IGameLogger gameLogger;


    public GameLoop(final IMediator mediator,
                    final IPossibleActionList possibleActionList,
                    final IBoard board,
                    final IPlayer firstPlayer,
                    final IPlayer secondPlayer,
                    final IGameLogger gameLogger) {
        this.activePlayer = firstPlayer;

        this.mediator = mediator;
        this.possibleActionList = possibleActionList;
        this.board = board;

        activePlayerController = new ActivePlayerController(firstPlayer, secondPlayer);
        gameStateController = new GameStateController(possibleActionList, mediator);
        gameProcessController = new GameProcessController(mediator, possibleActionList, board,gameLogger);
        kingStateController = new KingsStateController(possibleActionList, mediator, activePlayer);

        consoleBoardWriter = new ConsoleBoardWriter(mediator, board);

        this.gameLogger = gameLogger;
    }

    /**
     * Активация главного игрового цикла.
     */
    @Override
    public void activate() {
        kingStateController.addToSubscriber((ISubscriber) gameStateController);

        while (gameStateController.isMatchAlive()) {
            showAvailableMoves();
            consoleBoardWriter.printBoard();

            do {
                IAnswer answer = activePlayer.getAnswer(board, mediator, possibleActionList);
                gameProcessController.makeTurn(activePlayer, answer);

            } while (!gameProcessController.playerDidMove());

            activePlayer = activePlayerController.getNextPlayer();
            kingStateController.setActivePlayer(activePlayer);
            kingStateController.updateState();
        }

        consoleBoardWriter.printBoard();
        consoleBoardWriter.printMatchResult(gameStateController.getState());
    }

    private void showAvailableMoves() {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                IField field = board.getField(i, j);
                Figure figure = mediator.getFigure(field);
                if (figure != null) {
                    System.out.println(figure.getName() + " " + figure.getColor());
                    for (IField field1 : possibleActionList.getRealList(figure)) {
                        System.out.print("(" + field1.getXCoord() + ";" + field1.getYCoord() + ")" + " ");
                    }
                    System.out.print("\n");
                }
            }
        }
    }
}
