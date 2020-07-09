package neointernship.chess.game.model.figure;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.field.IField;

import java.lang.reflect.Field;
import java.util.ArrayList;

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
}
