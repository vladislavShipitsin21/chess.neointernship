package neointernship.bots.functionsH.bonus.midlegame;

import neointernship.bots.functionsH.bonus.Bonus;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.field.IField;

public class BonusKingPawn extends Bonus {

    private final static int MAX_SUB_BONUS = 3;
    private final double gamma;

    protected BonusKingPawn(final double price) {
        super(price);
        gamma = price / MAX_SUB_BONUS;
    }

    @Override
    public double getBonus(final Position position, final Color playerColor) {
        double result = 0;

        final IMediator mediator = position.getMediator();

        final Figure king = mediator.getKing(playerColor);
        final IField fieldKing = mediator.getField(king);
        final int offset = king.getColor() == Color.WHITE ? -1 : 1;


        if(fieldKing.getYCoord() == 6 || fieldKing.getYCoord() == 7) result += gamma;

        final Figure pawn2 = mediator.getFigure(BOARD.getField(fieldKing.getXCoord() + offset, 6));
        if (pawn2 != null) result += gamma;
        final Figure pawn3 = mediator.getFigure(BOARD.getField(fieldKing.getXCoord() + offset, 7));
        if (pawn3 != null) result += gamma;

        return result;
    }
}
