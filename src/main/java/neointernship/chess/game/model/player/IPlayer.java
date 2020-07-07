package neointernship.chess.game.model.player;

import neointernship.chess.game.model.Answer.IAnswer;
import neointernship.chess.game.model.playmap.board.Board;

public interface IPlayer {
    IAnswer makeTurn(final Board board);
    String getColor();
    String getName();
}
