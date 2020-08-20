package neointernship.chess.game.model.player;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.tree.BuilderTree;
import neointernship.tree.HelperBuilderTree;
import neointernship.tree.IBuilderTree;
import neointernship.tree.INode;

public abstract class BotTree extends Bot {

    protected IBuilderTree builderTree;
    protected int maxDepth;

    public BotTree(final String name, final Color color, final int maxDepth) {
        super(name,color);
        this.maxDepth = maxDepth;
    }

    @Override
    public IAnswer getAnswer(final IMediator mediator, final IPossibleActionList possibleActionList) {
        time.start();

        final Position startPosition = new Position(mediator, possibleActionList);

        final BuilderTree builderTree = new BuilderTree(maxDepth, getColor());
        final INode root = builderTree.getTree(startPosition);

        final IAnswer answer = HelperBuilderTree.getAnswer(root);

        time.finish();
        System.out.println(getName() + " - " + time.getTime());

        return answer;
    }
}
