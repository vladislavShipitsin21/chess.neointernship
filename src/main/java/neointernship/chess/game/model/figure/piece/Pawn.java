package neointernship.chess.game.model.figure.piece;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.Figure;

public class Pawn extends Figure {
    public Pawn(Color color) {
        super("Pawn", 'P', color, (short) 1);
    }
}
