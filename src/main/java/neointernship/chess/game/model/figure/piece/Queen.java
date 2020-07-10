package neointernship.chess.game.model.figure.piece;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.Collection;

public class Queen extends Figure {
    public Queen(Color color) {
        super("Queen", 'Q', color, (short) 8);
    }

}
