package neointernship.bots.functionsH.bonus;

import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;

public class BonusCountFigure extends Bonus {

    private static final int ALL_PRICE = 38;

    public BonusCountFigure() {
        super(0);
    }

    @Override
    public double getBonus(final Position position, final Color playerColor) {
        double result = 0;
        for (final Figure figure : position.getMediator().getFigures()) {
            if (figure.getClass() != King.class) {
                if (playerColor == figure.getColor()) {
                    result += figure.getPrice();
                } else {
                    result -= figure.getPrice();
                }
            }
        }
        return result / ALL_PRICE;
    }
}
