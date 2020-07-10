package neointernship.chess.game.gameplay.moveaction;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;

public interface IMoveActionCommand {
    boolean execute(Color color, IAnswer answer);
}
