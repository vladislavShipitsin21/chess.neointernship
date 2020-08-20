package neointernship.chess.game.model.player;

import neointernship.chess.game.model.enums.Color;
import neointernship.tree.BuilderTreeForkJoin;

public class MiniMaxForkJoin extends BotTree {

    public MiniMaxForkJoin(final Color color, final int maxDepth) {
        super("MiniMaxForkJoin", color,maxDepth);
        builderTree = new BuilderTreeForkJoin(maxDepth,getColor());
    }

}
