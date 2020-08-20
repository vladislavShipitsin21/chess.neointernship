package neointernship.bots.straregy;

import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.tree.BuilderTree;
import neointernship.tree.HelperBuilderTree;
import neointernship.tree.INode;

public class StrategyMiniMax extends Strategy {

    public StrategyMiniMax(Color color) {
        super(color);
    }

    @Override
    public IAnswer getAnswer(final Position startPosition) {

        final BuilderTree builderTree = new BuilderTree(2, getColor());
        final INode root = builderTree.getTree(startPosition);

        return HelperBuilderTree.getAnswer(root);
    }
}
