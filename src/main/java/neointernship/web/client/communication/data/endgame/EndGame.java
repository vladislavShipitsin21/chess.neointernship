package neointernship.web.client.communication.data.endgame;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import neointernship.chess.game.model.enums.EnumGameState;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("EndGame")
public class EndGame implements IEndGame {
    @JsonProperty
    private final EnumGameState gameState;

    public EndGame(@JsonProperty("gameState") final EnumGameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public EnumGameState getEnumGameState() {
        return gameState;
    }
}
