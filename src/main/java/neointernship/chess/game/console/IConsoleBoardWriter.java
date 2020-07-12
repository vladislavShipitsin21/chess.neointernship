package neointernship.chess.game.console;

import neointernship.chess.game.gameplay.gamestate.state.GameState;

public interface IConsoleBoardWriter {
    void printBoard();
    void printMatchResult(GameState gameState);
}
