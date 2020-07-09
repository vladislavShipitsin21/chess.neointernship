package neointernship.chess.game.model.figure.piece;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.Figure;

public class Queen extends Figure {
    public Queen(Color color) {
        super("Queen", 'Q', color, (short) 8);
    }
}
