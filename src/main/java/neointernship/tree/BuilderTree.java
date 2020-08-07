package neointernship.tree;

import neointernship.bots.modeling.Modeling;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;

import java.util.Map;

import static neointernship.chess.game.model.enums.Color.swapColor;

public class BuilderTree {

    private final int max_depth;
    private final Color activeColor;

    public BuilderTree(int max_depth, Color activeColor) {
        this.max_depth = max_depth;
        this.activeColor = activeColor;
    }

    /**
     * возвращает ссылку на корень дерева
     *
     * @param startPosition стартовая позиция - корень
     * @return
     */
    public INode getTree(final Position startPosition) {

        final INode root = new Node(startPosition);
        getSubTree(root,0);

        return root;
    }

    private INode getSubTree(final INode subRoot, int depth) {

        final boolean isMax = depth % 2 == 0;
        final Color currentColor = isMax ? activeColor : swapColor(activeColor);

        if(isEndTree(subRoot.getCore(),currentColor,depth))  return subRoot;

        depth++;

        final Map<Position, IAnswer> map = Modeling.modeling(subRoot.getCore(), currentColor);

        for (Map.Entry<Position, IAnswer> entry : map.entrySet()) {

            final INode child = new Node(entry.getKey());
            final IAnswer answer = entry.getValue();

            final IEdge edge = new Edge(child, answer);
            subRoot.addEdge(edge);

            getSubTree(child,depth);
        }
        return subRoot;
    }

    private boolean isEndTree(final Position position, final Color currentColor, final int depth) {
        return depth >= max_depth ;//|| TerminalBoss.isTerminal(position,currentColor);
    }

}
