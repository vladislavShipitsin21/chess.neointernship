package neointernship.chess.game.gameplay.activeplayercontroller;

import neointernship.chess.game.model.player.IPlayer;

public class ActivePlayerController implements IActivePlayerController {
    private final IPlayer firstPlayer;
    private final IPlayer secondPlayer;

    private IPlayer currentPlayer;

    public ActivePlayerController(final IPlayer firstPlayer, final IPlayer secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;

        currentPlayer = null;
    }


    @Override
    public void update() {
        currentPlayer = (currentPlayer != firstPlayer) ? firstPlayer : secondPlayer;
    }

    @Override
    public IPlayer getCurrentPlayer() {
        return currentPlayer;
    }
}
