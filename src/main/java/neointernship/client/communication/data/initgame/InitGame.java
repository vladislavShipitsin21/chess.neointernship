package neointernship.client.communication.data.initgame;

import com.fasterxml.jackson.annotation.*;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.IBoard;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("InitGame")
public class InitGame implements IInitGame{
    @JsonProperty
    private final Mediator mediator;
    @JsonProperty
    private final IBoard board;
    @JsonProperty
    private final Color color;

    public InitGame(@JsonProperty("mediator") final Mediator mediator,
                    @JsonProperty("board") final IBoard board,
                    @JsonProperty("color") final Color color) {
        this.mediator = mediator;
        this.board = board;
        this.color = color;
    }

    @Override
    public Mediator getMediator() {
        return mediator;
    }

    @Override
    public IBoard getBoard() {
        return board;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
