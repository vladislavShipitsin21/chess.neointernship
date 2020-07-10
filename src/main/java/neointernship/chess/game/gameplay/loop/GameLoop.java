package neointernship.chess.game.gameplay.loop;

import neointernship.chess.game.console.ConsoleBoardWriter;
import neointernship.chess.game.console.IConsoleBoardWriter;
import neointernship.chess.game.gameplay.activeplayercontroller.ActivePlayerController;
import neointernship.chess.game.gameplay.activeplayercontroller.IActivePlayerController;
import neointernship.chess.game.gameplay.gameprocesscontroller.GameProcessController;
import neointernship.chess.game.gameplay.gameprocesscontroller.IGameProcessController;
import neointernship.chess.game.gameplay.gameresultcontroller.GameResultController;
import neointernship.chess.game.gameplay.gameresultcontroller.IGameResultController;
import neointernship.chess.game.gameplay.kingstate.controller.IKingStateController;
import neointernship.chess.game.gameplay.kingstate.controller.KingsStateController;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.logger.GameLogger;

/**
 * Класс, реализующий основное ядро игры (игровой цикл)
 */
public class GameLoop implements IGameLoop {
    private final IMediator mediator;
    private final IPossibleActionList possibleActionList;
    private final IBoard board;
    private final IPlayer firstPlayer;
    private final IPlayer secondPlayer;

    private final IActivePlayerController activePlayerController;
    private final IGameResultController gameResultController;
    private final IGameProcessController gameProcessController;
    private final IKingStateController kingStateController;

    private final IConsoleBoardWriter consoleBoardWriter;
    private final GameLogger gameLogger;


    public GameLoop(final IMediator mediator,
                    final IPossibleActionList possibleActionList,
                    final IBoard board,
                    final IPlayer firstPlayer,
                    final IPlayer secondPlayer) {
        this.mediator = mediator;
        this.possibleActionList = possibleActionList;
        this.board = board;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;

        activePlayerController = new ActivePlayerController(firstPlayer, secondPlayer);
        gameResultController = new GameResultController();
        gameProcessController = new GameProcessController(mediator, possibleActionList, board);
        kingStateController = new KingsStateController(possibleActionList, mediator);

        consoleBoardWriter = new ConsoleBoardWriter();
        gameLogger = new GameLogger(firstPlayer, secondPlayer);
    }

    /**
     * Активация главного игрового цикла.
     */
    @Override
    public void activate() {
        gameLogger.logStartGame();

        while (gameResultController.isAlive()) {
            IPlayer activePlayer = activePlayerController.getNextPlayer();

            do {
                IAnswer answer = activePlayer.getAnswer(board, mediator, possibleActionList);

                gameProcessController.makeTurn(kingStateController.getState(activePlayer.getColor()), answer);
                kingStateController.updateState(activePlayer.getColor());


                consoleBoardWriter.printBoard(mediator);
            } while (!gameProcessController.playerDidMove());
        }

        gameResultController.showGameResult();
    }
}
