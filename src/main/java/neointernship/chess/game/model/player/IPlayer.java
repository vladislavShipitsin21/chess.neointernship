package neointernship.chess.game.model.player;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.playmap.board.IBoard;


public interface IPlayer {
    IAnswer makeTurn(final IBoard board);
    Color getColor();
    String getName();
}
