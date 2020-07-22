package neointernship.web.client.communication.data.initgame;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.IBoard;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("InitGameDto")
public class InitGameDto implements IInitGame {
    @JsonProperty
    private Mediator mediator;
    @JsonProperty
    private IBoard board;
    @JsonProperty
    private Color color;

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
