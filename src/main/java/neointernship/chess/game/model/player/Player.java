package neointernship.chess.game.model.player;

import neointernship.chess.game.model.playmap.board.Board;

public class Player implements IPlayer {
    private final String color;

    public Player(final String color) {
        this.color = color;
    }

    public void makeTurn(final Board board) {

    }

    public String getColor() {
        return color;
    }
}
