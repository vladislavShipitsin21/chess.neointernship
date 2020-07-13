package neointernship.chess.game.model.playmap.field;

import neointernship.chess.game.model.enums.Color;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return x == field.x &&
                y == field.y &&
                color == field.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, color);
    }
}
