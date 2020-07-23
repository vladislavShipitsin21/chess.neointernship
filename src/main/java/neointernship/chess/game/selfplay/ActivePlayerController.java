package neointernship.chess.game.selfplay;

import neointernship.chess.game.model.player.IPlayer;

public class ActivePlayerController {
    private final IPlayer firstPlayer;
    private final IPlayer secondPlayer;

    private IPlayer currentPlayer;

    public ActivePlayerController(final IPlayer firstPlayer, final IPlayer secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;

        currentPlayer = firstPlayer;
    }

    public IPlayer getNextPlayer() {
        currentPlayer = (currentPlayer == firstPlayer) ? secondPlayer : firstPlayer;
        return currentPlayer;
    }
}
