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

    private final static int MAX_SUB_BONUS = 6;
    private final double gamma;

    public BonusProtectionKing(final double price) {
        super(price);
        gamma = price / MAX_SUB_BONUS;
    }

    // перед ним 3 пешки и он не в центре
    @Override
    public double getBonus(final Position position, final Color playerColor) {
        double result = 0;

        final IMediator mediator = position.getMediator();

        final Figure king = mediator.getKing(playerColor);
        final IField fieldKing = mediator.getField(king);
        final int offset = king.getColor() == Color.WHITE ? -1 : 1;

        final Figure rook = mediator.getFigure(BOARD.getField(fieldKing.getXCoord(), 7));
        // проверка что не зажали ладью в углу

        if (fieldKing.getYCoord() == 6 && rook == null){
            result += 3 * gamma;
        }

        final Figure pawn1 = mediator.getFigure(BOARD.getField(fieldKing.getXCoord(), 5));
        if (pawn1 != null) result += gamma;
        final Figure pawn2 = mediator.getFigure(BOARD.getField(fieldKing.getXCoord() + offset, 6));
        if (pawn2 != null) result += gamma;
        final Figure pawn3 = mediator.getFigure(BOARD.getField(fieldKing.getXCoord() + offset, 7));
        if (pawn3 != null) result += gamma;

        return result;
    }
}
