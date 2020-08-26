package neointernship.tree;

import neointernship.bots.functionsH.FunctionWithBonus;
import neointernship.bots.functionsH.IFunctionsH;
import neointernship.bots.modeling.Modeling;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.util.Pair;

import static neointernship.chess.game.model.enums.Color.swapColor;

public class BuilderTreeWithBonus {

    private final int max_depth;
    private final Color playerColor;

    private IFunctionsH function;

    public BuilderTreeWithBonus(final Color playerColor, final int max_depth) {
        this.max_depth = max_depth;
        this.playerColor = playerColor;
    }

    /**
     * возвращает ссылку на корень дерева
     *
     * @param startPosition стартовая позиция - корень
     * @return
     */
    public INode getTree(final Position startPosition, final IFunctionsH functionsH) {
        function = functionsH;

        final INode root = new Node(startPosition);
        getSubTree(root, 0);

        return root;
    }

    private double getSubTree(final INode subRoot, final int depth) {

        final boolean isMax = depth % 2 == 0;
        final Color currentColor = isMax ? playerColor : swapColor(playerColor);

        final IGameState gameState = TerminalBoss.getStatePosition(subRoot.getCore(), currentColor);

        if (isEndTree(depth, gameState)) {

            final double value = function.price(subRoot.getCore(), playerColor, gameState);
            subRoot.getCore().setPrice(value);
            return value;
        }

        final Modeling modeling = new Modeling(subRoot.getCore(), currentColor);

        if (isMax) {

            subRoot.getCore().setPrice(Integer.MIN_VALUE);

            while (modeling.hasNext()) {

                final Pair<Position, IAnswer> pair = modeling.next();

                final INode child = new Node(pair.getFirst());
                final IAnswer answer = pair.getSecond();

                final IEdge edge = new Edge(child, answer);
                subRoot.addEdge(edge);

                double value = getSubTree(child, depth + 1);
                value = Math.max(subRoot.getCore().getPrice(), value);

                subRoot.getCore().setPrice(value);
            }
        } else {

            subRoot.getCore().setPrice(Integer.MAX_VALUE);

            while (modeling.hasNext()) {

                final Pair<Position, IAnswer> pair = modeling.next();

                final INode child = new Node(pair.getFirst());
                final IAnswer answer = pair.getSecond();

                final IEdge edge = new Edge(child, answer);
                subRoot.addEdge(edge);

                double value = getSubTree(child, depth + 1);
                value = Math.min(subRoot.getCore().getPrice(), value);

                subRoot.getCore().setPrice(value);
            }
        }

        return subRoot.getCore().getPrice();
    }

    private boolean isEndTree(final int depth, final IGameState gameState) {
        return depth >= max_depth || TerminalBoss.isTerminal(gameState);
    }
}
