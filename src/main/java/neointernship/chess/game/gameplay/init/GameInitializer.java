package neointernship.chess.game.gameplay;

import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.board.IBoard;

public class GameInitializer implements IGameInitializer {
    final IBoard gameBoard;

    public GameInitializer() {

        gameBoard = new Board();
    }

    @Override
    public void start() {

    }
}
