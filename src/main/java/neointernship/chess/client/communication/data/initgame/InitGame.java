package neointernship.chess.client.communication.data.initgame;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.IBoard;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("InitGame")
public class InitGame implements IInitGame{
    @JsonProperty
    private final Mediator mediator;
    private final IBoard board;
    private final Color color;

    public InitGame(@JsonProperty("mediator") final Mediator mediator,
                    @JsonProperty("board") final IBoard board,
                    @JsonProperty("color") final Color color) {
        this.mediator = mediator;
        this.board = board;
        this.color = color;
    }

    @JsonCreator
    public InitGame() {
        this.mediator = null;
        this.board = null;
        this.color = null;
    }

    public boolean validate() throws Exception {
        if (mediator == null) {
            throw new Exception("Отсутствует mediator");
        }
        if (board == null) {
            throw new Exception("Отсутствует board");
        }
        if (color == null) {
            throw new Exception("Отсутствует color");
        }
        return true;
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
