package neointernship.chess.game.gameplay.moveaction.commands.allow;

import neointernship.chess.game.model.answer.IAnswer;

public interface IAllowCommand {
    void execute(final IAnswer answer);
}
