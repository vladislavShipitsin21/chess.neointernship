package neointernship.chess.game.gameplay.gameprocesscontroller;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.logger.IGameLogger;
import neointernship.web.client.communication.message.ChessCodes;

public interface IGameProcessController {
    void makeTurn(final Color color, final IAnswer answer);
    ChessCodes getChessCode();
}
