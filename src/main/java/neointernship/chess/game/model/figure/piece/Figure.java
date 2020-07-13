package neointernship.chess.game.model.figure.piece;

import neointernship.chess.game.model.enums.Color;

import java.util.Objects;

/**
 * Интерфейс (абстрактный класс) для описания шахматных фигур.
 */
public abstract class Figure {

    private final String name;
    private final char gameSymbol;
    private final Color color;
    private final short price;

    /**
     * Конструктор фигуры
     *
     * @param name название фигуры
     * @param gameSymbol символ фигуры в игре
     * @param color цвет
     * @param price ценность
     */
    public Figure(final String name, final Character gameSymbol, final Color color, final short price) {
        this.name = name;
        this.gameSymbol = gameSymbol;
        this.color = color;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public char getGameSymbol() {
        return gameSymbol;
    }

    public Color getColor() {
        return color;
    }

    public short getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Figure figure = (Figure) o;
        return gameSymbol == figure.gameSymbol &&
                price == figure.price &&
                Objects.equals(name, figure.name) &&
                color == figure.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gameSymbol, color, price);
    }
}
