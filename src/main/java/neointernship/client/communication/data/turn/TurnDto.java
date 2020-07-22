package neointernship.client.communication.data.turn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import neointernship.chess.game.model.answer.IAnswer;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("TurnDto")
public class TurnDto implements ITurn {
    @JsonProperty
    private IAnswer answer;

    public boolean validate() throws Exception {
        if (answer == null){
            throw new Exception("Отсутствует answer");
        }
        return true;
    }

    @Override
    public IAnswer getAnswer() {
        return answer;
    }
}
