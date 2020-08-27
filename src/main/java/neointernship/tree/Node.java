package neointernship.tree;

import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;

import java.util.Collection;
import java.util.HashSet;

public class Node implements INode {

    private final Position core;
    private final Collection<IEdge> edgeSet;

    public Node(final Position core) {
        this.core = core;
        this.edgeSet = new HashSet<>();
    }

    @Override
    public Collection<IEdge> getEdges() {
        return edgeSet;
    }

    @Override
    public void addEdge(final IEdge edge) {
        edgeSet.add(edge);
    }

    @Override
    public Position getCore() {
        return core;
    }

    @Override
    public boolean isLeaf() {
        return edgeSet.isEmpty();
    }
}
