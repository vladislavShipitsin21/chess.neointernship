package neointernship.chess.game.gameplay.loop;

import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.model.answer.IAnswer;

public interface IGameLoop {
    void doIteration(final IAnswer answer);
    boolean isAlive();
    IGameState getMatchResult();
    void activate();
}
