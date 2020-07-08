package neointernship.chess.game.model.player;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.playmap.board.Board;

public class Player implements IPlayer {
    private final String color;
    private final String name;

    public Player(final String name, final String color) {
        this.color = color;
        this.name = name;
    }

    public IAnswer makeTurn(final Board board) {
        return null;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
