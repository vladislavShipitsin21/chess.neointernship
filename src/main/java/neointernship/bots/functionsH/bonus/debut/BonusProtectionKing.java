package neointernship.bots.functionsH.bonus.debut;

import neointernship.bots.functionsH.bonus.Bonus;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.field.IField;

/**
 * защита короля ( рокировка )
 */
public class BonusProtectionKing extends Bonus {

    public BonusProtectionKing(final double price) {
        super(price);
    }

    // перед ним 3 пешки и он не в центре
    @Override
    public double getBonus(final Position position, final Color playerColor) {
        double result = 0;

        final IMediator mediator = position.getMediator();

        final Figure king1 = mediator.getKing(playerColor);
        final Figure king2 = mediator.getKing(Color.swapColor(playerColor));

        if (isBonus(king1, mediator)) result += price;
        if (isBonus(king2, mediator)) result -= price;

        return result;
    }

    private boolean isBonus(final Figure king, final IMediator mediator) {
        final IField fieldKing = mediator.getField(king);
        final int offset = king.getColor() == Color.WHITE ? -1 : 1;

        if (fieldKing.getYCoord() != 6) return false;

        final Figure rook = mediator.getFigure(BOARD.getField(fieldKing.getXCoord(), 7));
        // проверка что не зажали ладью в углу
        if (rook != null) return false;
        // проверка что пешки защищающие короля на месте
        final Figure pawn1 = mediator.getFigure(BOARD.getField(fieldKing.getXCoord() + offset, 5));
        if (pawn1 == null) return false;
        final Figure pawn2 = mediator.getFigure(BOARD.getField(fieldKing.getXCoord() + offset, 6));
        if (pawn2 == null) return false;
        final Figure pawn3 = mediator.getFigure(BOARD.getField(fieldKing.getXCoord() + offset, 7));
        if (pawn3 == null) return false;

        return true;
    }
}
