package neointernship.chess.game.console;

import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.answer.IAnswer;

public interface IConsoleBoardWriter {
    void printBoard();
    void printPosition(final Position position);
}
