package neointernship.chess.game.gameplay.moveaction.commands;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.web.client.communication.message.TurnStatus;

public interface IMoveCommand {
    TurnStatus execute(final Color color, final IAnswer answer);
}
