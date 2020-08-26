package neointernship.bots.functionsH.bonus.midlegame;

import neointernship.bots.functionsH.bonus.Bonus;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;

public class BonusActivePositions extends Bonus {

    private final static int MAX_COUNT_ACTIONS = 137;

    protected BonusActivePositions(final double price) {
        super(price);
    }

    @Override
    public double getBonus(final Position position, final Color playerColor) {
        final IMediator mediator = position.getMediator();
        final PossibleActionList possibleActionList = position.getPossibleActionList();

        double result = 0;
        for (final Figure figure : mediator.getFigures(playerColor)) {
            final int offset = figure.getColor() == playerColor ? 1 : -1;
            result += offset * possibleActionList.getRealList(figure).size();
        }
        return result / MAX_COUNT_ACTIONS;
    }


}
