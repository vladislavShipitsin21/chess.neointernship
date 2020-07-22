package neointernship.chess.game.model.playmap.field;

import com.fasterxml.jackson.annotation.*;
import neointernship.chess.game.model.enums.Color;

import java.util.Objects;

public class Field implements IField {

    private final int x;
    private final int y;
    private final Color color;

    public Field(final int x, final int y) {
        this.x = x;
        this.y = y;
        color = initColor();
    }

    @JsonCreator
    public Field(final String field){
        final String[] params = field.split(":");
        this.x = Integer.parseInt(params[0].trim());
        this.y = Integer.parseInt(params[1].trim());
        this.color = Color.parseColor(params[2].trim());
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

    @Override
    @JsonValue
    public String toString() {
        return x + ":" + y + ":" + color;
    }
}
