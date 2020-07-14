package neointernship.chess.game.gameplay.gameprocesscontroller;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.logger.IGameLogger;

public interface IGameProcessController {
    void makeTurn(IPlayer player, IAnswer answer, IGameLogger gameLogger);
    boolean playerDidMove();
}
