package neointernship.bots.functionsH.bonus;

import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.board.IBoard;

public abstract class Bonus implements IBonus {

    public final static double priceOnePawn = 1. / 38;
    protected final static IBoard BOARD = new Board();
    protected final double price;

    protected Bonus(final double price) {
        this.price = price;
    }
}
