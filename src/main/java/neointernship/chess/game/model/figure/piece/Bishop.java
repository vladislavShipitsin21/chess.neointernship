package neointernship.chess.game.model.figure.piece;

import neointernship.chess.game.model.enums.Color;

public class Bishop extends Figure {
    public Bishop(final Color color) {
        super("Bishop", 'B', color, (short) 3);
    }
}
