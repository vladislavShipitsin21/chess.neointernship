package neointernship.chess.game.model.figure.piece;

import neointernship.chess.game.model.enums.Color;

public class Pawn extends Figure {
    public Pawn(final Color color) {
        super("Pawn", 'P', color, (short) 1);
    }
}
