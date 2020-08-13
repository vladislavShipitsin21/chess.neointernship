package neointernship.tree;

import neointernship.bots.functionsH.TargetFunction;
import neointernship.bots.modeling.Modeling;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.gameplay.gamestate.state.IGameState;
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
        getSubTree(root, 0, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);

        return root;
    }

    private double getSubTree(final INode subRoot, int depth, double alfa, double beta) {

        final boolean isMax = depth % 2 == 0;
        final Color currentColor = isMax ? activeColor : swapColor(activeColor);

        final IGameState gameState = TerminalBoss.getStatePosition(subRoot.getCore(), currentColor);

        if (isEndTree(depth, gameState)) {

            final double value = TargetFunction.price(subRoot.getCore(), activeColor, gameState);
            subRoot.getCore().setPrice(value);
            return value;
        }

        depth++;

        final Modeling modeling = new Modeling(subRoot.getCore(), currentColor);

        if (isMax) {

            subRoot.getCore().setPrice(Integer.MIN_VALUE);

            while (modeling.hasNext()) {

                Map.Entry<Position, IAnswer> entry = modeling.next();


                final INode child = new Node(entry.getKey());
                final IAnswer answer = entry.getValue();

                final IEdge edge = new Edge(child, answer);
                subRoot.addEdge(edge);

                double value = getSubTree(child, depth, alfa, beta);
                value = Math.max(subRoot.getCore().getPrice(), value);

                subRoot.getCore().setPrice(value);

                if (value > beta) {
                    return value;
                }

                alfa = Math.max(alfa, value);


            }
        } else {

            subRoot.getCore().setPrice(Integer.MAX_VALUE);

            while (modeling.hasNext()) {

                Map.Entry<Position, IAnswer> entry = modeling.next();


                final INode child = new Node(entry.getKey());
                final IAnswer answer = entry.getValue();

                final IEdge edge = new Edge(child, answer);
                subRoot.addEdge(edge);

                double value = getSubTree(child, depth, alfa, beta);
                value = Math.min(subRoot.getCore().getPrice(), value);

                subRoot.getCore().setPrice(value);

                if (value < alfa) {
                    return value;
                }

                beta = Math.min(beta, value);


            }
        }

        return subRoot.getCore().getPrice();
    }

    private boolean isEndTree(final int depth, final IGameState gameState) {
        return depth >= max_depth || TerminalBoss.isTerminal(gameState);
    }
}