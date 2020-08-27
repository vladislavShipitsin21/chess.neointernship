package neointernship.bots.straregy;

import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.answer.IAnswer;

public interface IStrategy {
    IAnswer getAnswer(final Position startPosition);
}
