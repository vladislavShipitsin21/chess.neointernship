package neointernship.chess.game.model.player;

import neointernship.bots.straregy.IStrategy;
import neointernship.bots.straregy.StrategyMixed;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.tree.BuilderTreeWithBonus;
import neointernship.tree.HelperBuilderTree;
import neointernship.tree.INode;

public class BotMixidStrategy extends Bot {

    private StrategyMixed strategy;

    public BotMixidStrategy(final Color color) {
        super("BotMixidStrategy", color);
        strategy = new StrategyMixed(color);
    }

    @Override
    public IAnswer getAnswer(final IMediator mediator, final IPossibleActionList possibleActionList) {
        time.start();

        final Position startPosition = new Position(mediator, possibleActionList);

        final IAnswer answer = strategy.getAnswer(startPosition);

        time.finish();
        System.out.println(time.getCountAddress() + ") " + getName() + " - " + time.getTime());
        return answer;
    }
}
