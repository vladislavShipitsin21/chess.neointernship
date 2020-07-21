package neointernship.chess.client.communication.data.initgame;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.IBoard;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InitGameDto implements IInitGame {
    private Mediator mediator;
    private IBoard board;
    private Color color;

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
