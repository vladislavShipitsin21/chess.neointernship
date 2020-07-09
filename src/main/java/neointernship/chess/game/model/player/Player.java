package neointernship.chess.game.model.player;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.board.IBoard;

public class Player implements IPlayer {
    private final Color color;
    private final String name;

    public Player(final String name, final Color color) {
        this.color = color;
        this.name = name;
    }

    public IAnswer makeTurn(final IBoard board) {
        return null; //todo
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
