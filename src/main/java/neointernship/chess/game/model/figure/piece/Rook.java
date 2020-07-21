package neointernship.chess.game.model.figure.piece;

import com.fasterxml.jackson.annotation.*;
import neointernship.chess.game.model.enums.Color;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonSubTypes.Type(value = Rook.class, name = "Rook")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rook extends Figure {
    public Rook(Color color) {
        super("Rook", 'R', color, (short) 5);
    }

    @JsonCreator
    public Rook(@JsonProperty("name") final String name,
                  @JsonProperty("gameSymbol") final Character gameSymbol,
                  @JsonProperty("color") final Color color,
                  @JsonProperty("price") final short price) {
        super(name, gameSymbol, color, price);
    }

    //@JsonCreator
    public Rook() {
    }

    //@JsonCreator
    public Rook(final String figure) {
        super(figure);
    }
}
