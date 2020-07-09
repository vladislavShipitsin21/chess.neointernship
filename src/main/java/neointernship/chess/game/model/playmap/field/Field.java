package neointernship.chess.game.model.playmap.field;

import neointernship.chess.game.model.enums.Color;

public class Field implements IField {

    private final int x;
    private final int y;
    private final Color color;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
        color = initColor();
    }

    public int getXCoord() {
        return x;
    }

    public int getYCoord() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    /**
     * Определяет какой цвет у клетки с координатами (x,y)
     * @return
     */
    private Color initColor(){
        if( (x + y) % 2 == 0) return Color.WHITE;
        return Color.BLACK;
    }

}