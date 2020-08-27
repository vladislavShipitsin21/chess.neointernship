package neointernship.bots.functionsH.bonus.midlegame;

import neointernship.bots.functionsH.bonus.Bonus;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.figure.piece.Pawn;
import neointernship.chess.game.model.mediator.IMediator;

public class BonusActivePositions extends Bonus {

    private final static int MAX_COUNT_ACTIONS = 97;
    private final double gamma;

    public BonusActivePositions(final double price) {
        super(price);
        gamma = price / MAX_COUNT_ACTIONS;
    }

    @Override
    public double getBonus(final Position position, final Color playerColor) {
        final IMediator mediator = position.getMediator();
        final PossibleActionList possibleActionList = position.getPossibleActionList();

        double result = 0;
        for (final Figure figure : mediator.getFigures(playerColor)) {
            if(figure.getClass() != King.class && figure.getClass() != Pawn.class) {
                final int realSizeActions = possibleActionList.getRealList(figure).size();
                result += realSizeActions;
            }
        }
        return result * gamma;
    }


}
