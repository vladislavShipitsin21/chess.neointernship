package neointernship.chess.game.model.figure.piece;

import com.fasterxml.jackson.annotation.*;
import neointernship.chess.game.model.enums.Color;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonSubTypes.Type(value = Pawn.class, name = "Pawn")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pawn extends Figure {
    //@JsonCreator
    public Pawn(Color color) {
        super("Pawn", 'P', color, (short) 1);
    }

    //@JsonCreator
    public Pawn(@JsonProperty("name") final String name,
                @JsonProperty("gameSymbol") final Character gameSymbol,
                @JsonProperty("color") final Color color,
                @JsonProperty("price") final short price) {
        super(name, gameSymbol, color, price);
    }
    @JsonCreator
    public Pawn(){
    }

    //@JsonCreator
    public Pawn(final String figure){
        super(figure);
    }
}
