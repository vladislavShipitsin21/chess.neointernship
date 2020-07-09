package neointernship.chess.game.model.figure.piece;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.Figure;

public class King extends Figure {
    public King( Color color) {
        super("King", 'K', color, Short.MAX_VALUE);
    }
}
