package neointernship.chess.game.model.player;

import neointernship.chess.game.model.playmap.board.Board;

public interface IPlayer {
    void makeTurn(final Board board);
    String getColor();
}
