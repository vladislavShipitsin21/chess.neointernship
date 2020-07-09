package neointernship.chess.game.model.figure.piece;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.Collection;

public class Rook extends Figure {
    public Rook(Color color) {
        super("Rook", 'R', color, (short) 5);
    }

}
