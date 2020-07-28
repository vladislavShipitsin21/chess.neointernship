package neointernship.web.client.communication.data.update;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.web.client.communication.message.ChessCodes;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Update implements IUpdate {
    private final IAnswer answer;
    private final ChessCodes chessCode;

    public Update(@JsonProperty("answer") final IAnswer answer, final ChessCodes chessCode) {
        this.answer = answer;
        this.chessCode = chessCode;
    }

    @Override
    public IAnswer getAnswer() {
        return answer;
    }

    @Override
    public ChessCodes getChessCode() {
        return chessCode;
    }
}
