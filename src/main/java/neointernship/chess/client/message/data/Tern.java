package neointernship.chess.client.message.data;

import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;

public class Tern implements Data<IAnswer> {
    private final IAnswer answer;

    public Tern(final int startX, final int startY, final int finishX, final int finishY) {
        answer = new Answer(startX, startY, finishX, finishY);
    }

    @Override
    public IAnswer getData() {
        return answer;
    }
}
