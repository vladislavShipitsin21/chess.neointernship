package neointernship.chess.game.model.player;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.playmap.board.IBoard;

public interface IPlayer {
    IAnswer makeTurn(final IBoard board);
}
