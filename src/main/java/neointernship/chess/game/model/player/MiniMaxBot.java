package neointernship.chess.game.model.player;

import neointernship.chess.game.model.enums.Color;
import neointernship.tree.BuilderTree;

public class MiniMaxBot extends BotTree {

    public MiniMaxBot(final Color color, final int maxDepth) {
        super("MiniMax",color,maxDepth);
        builderTree = new BuilderTree(maxDepth, getColor());
    }
}
