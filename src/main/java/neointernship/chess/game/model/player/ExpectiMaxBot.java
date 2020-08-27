package neointernship.chess.game.model.player;

import neointernship.chess.game.model.enums.Color;
import neointernship.tree.BuilderTreeExpectiMax;

public class ExpectiMaxBot extends BotTree {

    public ExpectiMaxBot(final Color color, final int max_depth) {
        super("ExpectiMax", color,max_depth);
        builderTree = new BuilderTreeExpectiMax(maxDepth, getColor());
    }
}
