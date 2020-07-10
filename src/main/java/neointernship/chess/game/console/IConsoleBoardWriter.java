package neointernship.chess.game.console;

import neointernship.chess.game.gameplay.gamestate.state.GameState;
import neointernship.chess.game.model.mediator.IMediator;

public interface IConsoleBoardWriter {
    void printBoard(final IMediator mediator);
    void printMatchResult(GameState gameState);
}