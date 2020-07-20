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
import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.gameplay.kingstate.controller.IKingStateController;
import neointernship.chess.game.gameplay.kingstate.controller.KingsStateController;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.board.IBoard;
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
        this.gameLogger = gameLogger;

        activePlayerController = new ActivePlayerController(firstPlayer, secondPlayer);
        gameStateController = new GameStateController(possibleActionList, mediator, gameLogger);
        gameProcessController = new GameProcessController(mediator, possibleActionList, board);
        kingStateController = new KingsStateController(possibleActionList, mediator, activePlayer);

        consoleBoardWriter = new ConsoleBoardWriter(mediator, board);
        kingStateController.addToSubscriber((ISubscriber) gameStateController);
    }

    /**
     * Активация главного игрового цикла.
     */
    @Override
    public void doIteration(final IAnswer answer) {
        do {
            gameProcessController.makeTurn(activePlayer, answer, gameLogger);
        } while (!gameProcessController.playerDidMove());

        activePlayer = activePlayerController.getCurrentPlayer();
        kingStateController.setActivePlayer(activePlayer);
        kingStateController.updateState();

        consoleBoardWriter.printBoard();
        gameStateController.showResults();
    }

    @Override
    public boolean isAlive() {
        return gameStateController.isMatchAlive();
    }

    @Override
    public IGameState getMatchResult() {
        return gameStateController.getState();
    }
}
