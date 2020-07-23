package neointernship.chess.game.gameplay.loop;

import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.web.client.communication.message.ChessCodes;

public interface IGameLoop {
    ChessCodes doIteration(final IAnswer answer);
    boolean isAlive();
    IGameState getMatchResult();
}
