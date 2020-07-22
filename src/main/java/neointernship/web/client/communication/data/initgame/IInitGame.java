package neointernship.web.client.communication.data.initgame;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.IBoard;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = InitGame.class, name = "InitGame"),
        @JsonSubTypes.Type(value = InitGameDto.class, name = "InitGameDto"),
})
public interface IInitGame {
    Mediator getMediator();
    IBoard getBoard();
    Color getColor();
}
