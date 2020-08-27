package neointernship.chess.game.model.player;

import neointernship.chess.game.model.enums.Color;
import neointernship.tree.BuilderTreeAB;

public class MiniMaxAB extends BotTree {

    public MiniMaxAB(final Color color, final int max_depth) {
        super("MiniMaxAB", color,max_depth);
        builderTree = new BuilderTreeAB(max_depth, getColor());
    }
}
