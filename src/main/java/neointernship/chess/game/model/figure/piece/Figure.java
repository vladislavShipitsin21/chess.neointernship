package neointernship.chess.game.model.figure.piece;

import com.fasterxml.jackson.annotation.*;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.factory.Factory;

/**
 * Интерфейс (абстрактный класс) для описания шахматных фигур.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Bishop.class, name = "Bishop"),
        @JsonSubTypes.Type(value = King.class, name = "King"),
        @JsonSubTypes.Type(value = Knight.class, name = "Knight"),
        @JsonSubTypes.Type(value = Pawn.class, name = "Pawn"),
        @JsonSubTypes.Type(value = Queen.class, name = "Queen"),
        @JsonSubTypes.Type(value = Rook.class, name = "Rook"),
})
public abstract class Figure {

    @JsonProperty
    private final String name;
    @JsonProperty
    private final char gameSymbol;
    @JsonProperty
    private final Color color;
    @JsonProperty
    private final short price;

    /**
     * Конструктор фигуры
     *
     * @param name название фигуры
     * @param gameSymbol символ фигуры в игре
     * @param color цвет
     * @param price ценность
     */

    @JsonCreator
    public Figure(@JsonProperty("name") final String name,
                  @JsonProperty("gameSymbol") final Character gameSymbol,
                  @JsonProperty("color") final Color color,
                  @JsonProperty("price") final short price) {
        this.name = name;
        this.gameSymbol = gameSymbol;
        this.color = color;
        this.price = price;
    }

    //@JsonCreator
    public Figure(final String figure) {
        final String[] params = figure.split(":");
        Factory factory = new Factory();
        Figure figure1 = factory.createFigure(params[1].trim().charAt(0), Color.parseColor(params[2].trim()));
        this.name = figure1.getName();
        this.gameSymbol = figure1.getGameSymbol();
        this.color = figure1.getColor();
        this.price = figure1.getPrice();
    }

    //@JsonCreator
    protected Figure() {
        this.name = null;
        this.gameSymbol = 0;
        this.color = null;
        this.price = 0;
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

   /* @Override
    @JsonValue
    public String toString() {
        return name + ":" + gameSymbol + ":" + color + ":" + price;
    }*/
}
