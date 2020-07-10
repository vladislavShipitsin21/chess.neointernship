package neointernship.chess.game.gameplay.gameprocesscontroller;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.KingState;

public interface IGameProcessController {
    void makeTurn(KingState kingState, IAnswer answer);
    boolean playerDidMove();
}
