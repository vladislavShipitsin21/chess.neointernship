package neointernship.chess.game.gameplay.activeplayercontroller;

import neointernship.chess.game.model.player.IPlayer;

public class ActivePlayerController implements IActivePlayerController {
    private final IPlayer firstPlayer;
    private final IPlayer secondPlayer;

    private final IPlayer currentPlayer;

    public ActivePlayerController(final IPlayer firstPlayer, final IPlayer secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;

        currentPlayer = secondPlayer;
    }

    @Override
    public IPlayer getNextPlayer() {
        return (currentPlayer == firstPlayer) ? secondPlayer : firstPlayer;
    }
}
