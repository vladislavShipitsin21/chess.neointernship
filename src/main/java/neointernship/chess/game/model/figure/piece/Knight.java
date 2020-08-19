package neointernship.chess.game.model.figure.piece;

import neointernship.chess.game.model.enums.Color;

public class Knight extends Figure {
    public Knight(final Color color) {
        super("Knight", 'N', color, (short) 3);
    }
}
