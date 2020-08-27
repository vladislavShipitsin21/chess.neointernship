package neointernship.chess.game.model.figure.piece;

import neointernship.chess.game.model.enums.Color;


public class Rook extends Figure {
    public Rook(final Color color) {
        super("Rook", 'R', color, (short) 5);
    }
}
