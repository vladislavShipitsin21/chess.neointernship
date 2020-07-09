package neointernship.chess.game.gameplay.loop;

import neointernship.chess.game.gameplay.activeplayercontroller.ActivePlayerController;
import neointernship.chess.game.gameplay.activeplayercontroller.IActivePlayerController;
import neointernship.chess.game.gameplay.gameresultcontroller.GameResultController;
import neointernship.chess.game.gameplay.gameresultcontroller.IGameResultController;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.board.IBoard;

public class GameLoop implements IGameLoop {
    private final IActivePlayerController activePlayerController;
    private final IGameResultController gameResultController;

    public GameLoop(final IMediator mediator,
                    final IPossibleActionList possibleActionList,
                    final IBoard board,
                    final IPlayer firstPlayer,
                    final IPlayer secondPlayer) {

        activePlayerController = new ActivePlayerController(firstPlayer, secondPlayer);
        gameResultController = new GameResultController();
    }

    @Override
    public void activate() {
        while (gameResultController.isAlive()) {
            IPlayer activePlayer = activePlayerController.getNextPlayer();

            do {
                IAnswer answer = activePlayer.makeTurn();
                
            } while ();
        }
    }
}
