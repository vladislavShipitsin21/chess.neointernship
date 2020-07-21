package neointernship.chess.client.communication.data.turn;

import neointernship.chess.game.model.answer.IAnswer;

public class TurnDto implements ITurn {
    private final IAnswer answer;

    public TurnDto(final IAnswer answer) {
        this.answer = answer;
    }

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
