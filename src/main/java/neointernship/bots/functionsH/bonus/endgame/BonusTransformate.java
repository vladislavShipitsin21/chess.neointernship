package neointernship.bots.functionsH.bonus.endgame;

import neointernship.bots.functionsH.bonus.Bonus;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.Pawn;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.field.IField;

/**
 * бонус за превращение пешки
 */
public class BonusTransformate extends Bonus {

    protected BonusTransformate(final double price) {
        super(price);
    }

    @Override
    public double getBonus(final Position position, final Color playerColor) {
        // чем дальше она от начала  тем больше бонус
        // пешек 8, максимальное расстояние 7
        final IMediator mediator = position.getMediator();
        double result = 0;

        for(final Figure figure : mediator.getFigures()){
            final int coordStartX = playerColor == Color.WHITE ? 6 : 1;
            final int offset = playerColor == Color.WHITE ? -1 : 1;

            if(figure.getClass() == Pawn.class){
                final IField field = mediator.getField(figure);
                final int div = Math.abs(coordStartX - field.getXCoord());

                result += offset * price * div / 7;
            }
        }
        return result;
    }
}
