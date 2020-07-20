package neointernship.chess.web.client.message.data;

import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;

public class Turn implements Data<IAnswer> {
    private final IAnswer answer;

    public Turn(final int startX, final int startY, final int finishX, final int finishY) {
        answer = new Answer(startX, startY, finishX, finishY);
    }

    @Override
    public IAnswer getData() {
        return answer;
    }
}
