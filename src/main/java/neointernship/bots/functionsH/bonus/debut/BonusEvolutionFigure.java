package neointernship.bots.functionsH.bonus.debut;

import neointernship.bots.functionsH.bonus.Bonus;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Bishop;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.Knight;
import neointernship.chess.game.model.figure.piece.Queen;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.story.IStoryGame;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * развитие фигур
 */
public class BonusEvolutionFigure extends Bonus {

    private final static int MAX_FIGURE = 4;

    private final double gamma;

    private final HashMap<Class,Integer> hoNeedActive;

    public BonusEvolutionFigure(final double price) {
        super(price);
        gamma = price / MAX_FIGURE;

        hoNeedActive = new HashMap<>();
        hoNeedActive.put(Knight.class,8);
        hoNeedActive.put(Bishop.class,13);
    }

    @Override
    public double getBonus(final Position position, final Color playerColor) {
        double result = 0;

        final IMediator mediator = position.getMediator();
        final Collection<Figure> figures = mediator.getFigures(playerColor);
        final PossibleActionList possibleActionList = position.getPossibleActionList();

        for (final Figure figure : figures) {
            if(hoNeedActive.containsKey(figure.getClass())) {
                final int realActive = possibleActionList.getRealList(figure).size();
                final int maxActive = hoNeedActive.get(figure.getClass());
                result += gamma * (realActive / maxActive);
            }
        }
        return result;
    }
}

