package neointernship.chess.client.communication.data.turn;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import neointernship.chess.game.model.answer.IAnswer;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Turn implements ITurn{
    private final IAnswer answer;

    public Turn(final IAnswer answer) {
        this.answer = answer;
    }

    @Override
    public IAnswer getAnswer() {
        return answer;
    }
}
