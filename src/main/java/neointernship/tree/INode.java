package neointernship.tree;

import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;

import java.util.Collection;

public interface INode {
    Position getCore();

    Collection<IEdge> getEdges();

    void addEdge(final IEdge edge);

    boolean isLeaf();
}
