package neointernship.chess.game.gameplay.gameprocesscontroller;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.player.IPlayer;

public interface IGameProcessController {
    void makeTurn(IPlayer player, IAnswer answer);
    boolean playerDidMove();
}
