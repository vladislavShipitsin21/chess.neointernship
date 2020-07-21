package neointernship.chess.game.model.figure.piece;

import com.fasterxml.jackson.annotation.*;
import neointernship.chess.game.model.enums.Color;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonSubTypes.Type(value = Bishop.class, name = "Bishop")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bishop extends Figure {

    public Bishop(Color color) {
        super("Bishop", 'B', color, (short) 3);
    }

    @JsonCreator
    public Bishop(@JsonProperty("name") final String name,
                  @JsonProperty("gameSymbol") final Character gameSymbol,
                  @JsonProperty("color") final Color color,
                  @JsonProperty("price") final short price) {
        super(name, gameSymbol, color, price);
    }

    //@JsonCreator
    public Bishop() {

    }

    //@JsonCreator
    public Bishop(final String figure) {
        super(figure);
    }
}
