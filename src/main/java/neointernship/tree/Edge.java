package neointernship.tree;

import neointernship.chess.game.model.answer.IAnswer;

public class Edge implements IEdge {

    private final INode child;
    private final IAnswer actions;

    public Edge(final INode child, final IAnswer actions) {
        this.child = child;
        this.actions = actions;
    }

    @Override
    public IAnswer getAction() {
        return actions;
    }

    @Override
    public INode getChild() {
        return child;
    }

}
