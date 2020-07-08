package neointernship.chess.game.model.figure.piece;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.Figure;

public class Bishop extends Figure {
    public Bishop(Color color) {
        super("Bishop", 'B', color, (short) 3);
    }
}
