package neointernship.chess.game.model.figure.piece;

import com.fasterxml.jackson.annotation.*;
import neointernship.chess.game.model.enums.Color;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonSubTypes.Type(value = Knight.class, name = "Knight")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Knight extends Figure {

    public Knight(Color color) {
        super("Knight", 'N', color, (short) 3);
    }

    @JsonCreator
    public Knight(@JsonProperty("name") final String name,
                  @JsonProperty("gameSymbol") final Character gameSymbol,
                  @JsonProperty("color") final Color color,
                  @JsonProperty("price") final short price) {
        super(name, gameSymbol, color, price);
    }

    //@JsonCreator
    public Knight() {

    }

    //@JsonCreator
    public Knight(final String figure) {
        super(figure);
    }
}
