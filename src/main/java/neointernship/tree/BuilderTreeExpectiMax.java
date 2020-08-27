package neointernship.tree;

import neointernship.bots.functionsH.TargetFunction;
import neointernship.bots.modeling.Modeling;
import neointernship.bots.probability.Probability;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.util.Pair;

import java.util.Collection;
import java.util.Map;

import static neointernship.chess.game.model.enums.Color.swapColor;

public class BuilderTreeExpectiMax implements IBuilderTree {

    private final int max_depth;
    private final Color activeColor;

    public BuilderTreeExpectiMax(final int max_depth, final Color activeColor) {
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
        getSubTree(root, 0);

        return root;
    }

    private double getSubTree(final INode subRoot, int depth) {

        final boolean isMax = depth % 2 == 0;
        final Color currentColor = isMax ? activeColor : swapColor(activeColor);
        final Position actualCore = subRoot.getCore();

        final IGameState gameState = TerminalBoss.getStatePosition(actualCore, currentColor);

        if (isEndTree(depth, gameState)) {
            final double value = TargetFunction.price(actualCore, activeColor, gameState);
            actualCore.setPrice(value);
            return value;
        }

        depth++;

        final Modeling modeling = new Modeling(actualCore, currentColor);
        final Collection<Pair<Position, IAnswer>> modelingPositions = modeling.getAll();

        if (isMax) {

            actualCore.setPrice(Integer.MIN_VALUE);

            for (final Pair<Position, IAnswer> pair : modelingPositions) {

                final INode child = new Node(pair.getFirst());
                final IAnswer answer = pair.getSecond();

                final IEdge edge = new Edge(child, answer);
                subRoot.addEdge(edge);

                double value = getSubTree(child, depth);
                value = Math.max(actualCore.getPrice(), value);

                actualCore.setPrice(value);

            }
        } else {

            actualCore.setPrice(0);
            final double prob = Probability.uniform.apply(modelingPositions.size());

            for (final Pair<Position, IAnswer> pair : modelingPositions) {

                final INode child = new Node(pair.getFirst());
                final IAnswer answer = pair.getSecond();

                final IEdge edge = new Edge(child, answer);
                subRoot.addEdge(edge);

                final double value = prob * getSubTree(child, depth);

                actualCore.setPrice(actualCore.getPrice() + value);
            }
        }

        return actualCore.getPrice();
    }

    private boolean isEndTree(final int depth, final IGameState gameState) {
        return depth >= max_depth || TerminalBoss.isTerminal(gameState);
    }
}
