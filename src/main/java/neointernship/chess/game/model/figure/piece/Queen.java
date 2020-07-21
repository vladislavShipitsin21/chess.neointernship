package neointernship.chess.game.model.figure.piece;

import com.fasterxml.jackson.annotation.*;
import neointernship.chess.game.model.enums.Color;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonSubTypes.Type(value = Queen.class, name = "Queen")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Queen extends Figure {
    //@JsonCreator
    public Queen(Color color) {
        super("Queen", 'Q', color, (short) 8);
    }

    @JsonCreator
    public Queen(@JsonProperty("name") final String name,
                  @JsonProperty("gameSymbol") final Character gameSymbol,
                  @JsonProperty("color") final Color color,
                  @JsonProperty("price") final short price) {
        super(name, gameSymbol, color, price);
    }

    //@JsonCreator
    public Queen() {

    }

    //@JsonCreator
    public Queen(final String figure) {
        super(figure);
    }
}
