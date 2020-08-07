package neointernship.tree;

import neointernship.chess.game.model.answer.IAnswer;

import java.util.Collection;

public interface IEdge {
    IAnswer getAction();

    INode getChild();
}
