package neointernship.bots.functionsH.bonus.midlegame;

import neointernship.bots.functionsH.bonus.Bonus;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.gameplay.kingstate.update.KingIsAttackedComputation;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;

/**
 * бонус за атаку на короля
 */
public class BonusAttackKing extends Bonus {

    private final static int GOLD_CONSTANT = 16;
    private final static int GOLD_CONSTANT_HALF = GOLD_CONSTANT / 2;
    private final double gamma;

    public BonusAttackKing(final double price) {
        super(price);

        gamma = price / GOLD_CONSTANT;
    }

    @Override
    public double getBonus(final Position position, final Color playerColor) {
        final IMediator mediator = position.getMediator();
        final IPossibleActionList list = position.getPossibleActionList();
        final KingIsAttackedComputation kingIsAttacked = new KingIsAttackedComputation(list,mediator);
        double result = 0;

        if(kingIsAttacked.kingIsAttacked(Color.swapColor(playerColor))){
            result += GOLD_CONSTANT_HALF * gamma;
        }

        final Figure kingOpponent = mediator.getKing(Color.swapColor(playerColor));
        final int sizeKingActions = list.getRealList(kingOpponent).size();
        final int bonus = GOLD_CONSTANT_HALF - sizeKingActions;

        result += gamma * bonus;

        return result;
    }
}
