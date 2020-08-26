package neointernship.bots.functionsH.bonus.midlegame;

import neointernship.bots.functionsH.bonus.Bonus;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.gameplay.kingstate.update.KingIsAttackedComputation;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;

/**
 * бонус за атаку на короля
 */
public class BonusAttackKing extends Bonus {

    public BonusAttackKing(final double price) {
        super(price);
    }

    @Override
    public double getBonus(final Position position, final Color playerColor) {
        final IMediator mediator = position.getMediator();
        final IPossibleActionList list = position.getPossibleActionList();
        final KingIsAttackedComputation kingIsAttacked = new KingIsAttackedComputation(list,mediator);

        double result = 0;
        if(kingIsAttacked.kingIsAttacked(playerColor)){
            result -= price;
        }
        if(kingIsAttacked.kingIsAttacked(Color.swapColor(playerColor))){
            result += price;
        }
        return result;
    }
}
