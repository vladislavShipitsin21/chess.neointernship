package neointernship.bots.functionsH.bonus.endgame;

import neointernship.bots.functionsH.bonus.Bonus;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.field.IField;

/**
 * чем меньше у короля противника ходов тем лучше
 */
public class BonusActiveKing extends Bonus {

    protected BonusActiveKing(final double price) {
        super(price);
    }

    @Override
    public double getBonus(final Position position, final Color playerColor) {
        final IMediator mediator = position.getMediator();

        final Figure kingMy = mediator.getKing(playerColor);
        final Figure kingOpponent = mediator.getKing(Color.swapColor(playerColor));
        final IPossibleActionList possibleActionList = position.getPossibleActionList();


        double result = 0;

        result += possibleActionList.getRealList(kingMy).size() * price / 8;
        result -= possibleActionList.getRealList(kingOpponent).size() * price / 8;
        return result;
    }
}
