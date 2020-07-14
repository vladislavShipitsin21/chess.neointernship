package neointernship.chess.game.gameplay.moveaction.commands;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.logger.IGameLogger;

public interface IMoveCommand {
    boolean execute(IPlayer player, IAnswer answer, IGameLogger gameLogger);
}
