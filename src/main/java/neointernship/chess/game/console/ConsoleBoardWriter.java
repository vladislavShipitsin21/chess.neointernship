package neointernship.chess.game.console;

import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.field.Field;

public class ConsoleBoardWriter implements IConsoleBoardWriter {
    final int boardSize = 8;

    @Override
    public void printBoard(final IMediator mediator) {
        System.out.println("Game board:");
        System.out.format("%6d%5d%5d%5d%5d%5d%5d%5d\n\n", 0, 1, 2, 3, 4, 5, 6, 7);

        for (int i = 0; i < boardSize; i++) {
            System.out.format("%d", i);
            for (int j = 0; j < boardSize; j++) {
                if (mediator.getFigure(new Field(i, j)) == null) {
                    System.out.format("%5s", ".");
                } else {
                    System.out.format("%5s",  mediator.getFigure(new Field(i, j)).getGameSymbol());
                }
            }
            System.out.print("\n\n");
        }
    }
}
