package neointernship.tree;

import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;

public interface IBuilderTree {
    INode getTree(final Position startPosition);
}
