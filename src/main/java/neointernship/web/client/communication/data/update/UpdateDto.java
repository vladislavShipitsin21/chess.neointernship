package neointernship.web.client.communication.data.update;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.web.client.communication.data.DataErrorCode;
import neointernship.web.client.communication.data.DataException;
import neointernship.web.client.communication.message.ChessCodes;

public class UpdateDto implements IUpdate {
    private IAnswer answer;
    private ChessCodes chessCode;

    public boolean validate() throws Exception {
        if (answer == null) {
            throw new DataException(DataErrorCode.NOT_ANSWER);
        }
        if (chessCode == null) {
            throw new DataException(DataErrorCode.NOT_CHESS_CODE);
        }
        return true;
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
