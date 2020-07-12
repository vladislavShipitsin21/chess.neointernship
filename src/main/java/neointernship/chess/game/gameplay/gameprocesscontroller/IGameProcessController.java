package neointernship.chess.game.gameplay.gameprocesscontroller;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;

public interface IGameProcessController {
    void makeTurn(Color color, IAnswer answer);
    boolean playerDidMove();
}
