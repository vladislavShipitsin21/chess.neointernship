package neointernship.bots.functionsH.bonus.midlegame;

import neointernship.bots.functionsH.bonus.Bonus;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.field.IField;

public class BonusKingPawn extends Bonus {

    protected BonusKingPawn(final double price) {
        super(price);
    }

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

        if(fieldKing.getYCoord() != 6 && fieldKing.getYCoord() != 7) return false;

        final Figure pawn2 = mediator.getFigure(BOARD.getField(fieldKing.getXCoord() + offset, 6));
        if (pawn2 == null) return false;
        final Figure pawn3 = mediator.getFigure(BOARD.getField(fieldKing.getXCoord() + offset, 7));
        if (pawn3 == null) return false;

        return true;
    }
}
