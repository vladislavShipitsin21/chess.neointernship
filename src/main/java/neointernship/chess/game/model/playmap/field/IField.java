package neointernship.chess.game.model.playmap.field;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.Figure;

public interface IField {
    int getXCoord();
    int getYCoord();
    Figure getFigure();
    Color getColor();

    boolean isEmpty();
    boolean isAttacked();
}