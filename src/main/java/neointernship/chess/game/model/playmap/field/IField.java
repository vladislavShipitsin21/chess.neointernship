package neointernship.chess.game.model.playmap.field;

import neointernship.chess.game.model.enums.Color;

public interface IField {
    int getXCoord();
    int getYCoord();
    Color getColor();
}