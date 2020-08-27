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

    private final static int MAX_DEFERENT = 7;
    private final double gamma;
    protected BonusTransformate(final double price) {
        super(price);
        gamma = price / MAX_DEFERENT;
    }

    @Override
    public double getBonus(final Position position, final Color playerColor) {
        final IMediator mediator = position.getMediator();
        double result = 0;
        int countPawn = 0;

        for(final Figure figure : mediator.getFigures(playerColor)){

            if(figure.getClass() == Pawn.class){
                countPawn++;
                final int coordStartX = playerColor == Color.WHITE ? 6 : 1;

                final IField field = mediator.getField(figure);
                final int div = Math.abs(coordStartX - field.getXCoord());

                result += div;
            }
        }
        return (result * gamma) / countPawn;
    }
}
