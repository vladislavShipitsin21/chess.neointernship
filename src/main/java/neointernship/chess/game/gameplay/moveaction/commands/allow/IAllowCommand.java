package neointernship.chess.game.gameplay.moveaction.commands.allow;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.web.client.communication.message.ChessCodes;

public interface IAllowCommand {
    void execute(final IAnswer answer);
    ChessCodes getChessCode();
}
