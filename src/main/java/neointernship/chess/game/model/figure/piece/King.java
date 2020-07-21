package neointernship.chess.game.model.figure.piece;

import com.fasterxml.jackson.annotation.*;
import neointernship.chess.game.model.enums.Color;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonSubTypes.Type(value = King.class, name = "King")
@JsonIgnoreProperties(ignoreUnknown = true)
public class King extends Figure {

    public King( Color color) {
        super("King", 'K', color, Short.MAX_VALUE);
    }

   // @JsonCreator
    public King(@JsonProperty("name") final String name,
                  @JsonProperty("gameSymbol") final Character gameSymbol,
                  @JsonProperty("color") final Color color,
                  @JsonProperty("price") final short price) {
        super(name, gameSymbol, color, price);
    }

    @JsonCreator
    public King() {

    }

    @JsonCreator
    public King(final String figure) {
        super(figure);
    }
}
