package neointernship.tree;

import neointernship.chess.game.model.answer.IAnswer;

public interface IEdge {
    IAnswer getAction();

    INode getChild();
}
