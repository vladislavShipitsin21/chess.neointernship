package neointernship.tree;

import neointernship.chess.game.gameplay.gamestate.controller.draw.MyPosition;

import java.util.Collection;
import java.util.HashSet;

public class MyNode {

    private final MyPosition core;
    private final Collection<IEdge> edgeSet;

    public MyNode(final MyPosition core) {
        this.core = core;
        this.edgeSet = new HashSet<>();
    }

    public Collection<IEdge> getEdges() {
        return edgeSet;
    }


    public void addEdge(final IEdge edge) {
        edgeSet.add(edge);
    }


    public MyPosition getCore() {
        return core;
    }

    public boolean isLeaf() {
        return edgeSet.isEmpty();
    }
}
