package neointernship.tree;

import neointernship.bots.functionsH.TargetFunction;
import neointernship.bots.modeling.Modeling;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.util.Pair;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static neointernship.chess.game.model.enums.Color.swapColor;

public class BuilderTreeThread implements IBuilderTree{
    private final int max_depth;
    private final Color activeColor;

    public BuilderTreeThread(final int max_depth, final Color activeColor) {
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

    private double getSubTree(final INode subRoot,final int depth) {

        final boolean isMax = depth % 2 == 0;
        final Color currentColor = isMax ? activeColor : swapColor(activeColor);

        final IGameState gameState = TerminalBoss.getStatePosition(subRoot.getCore(), currentColor);

        if (isEndTree(depth, gameState)) {

            final double value = TargetFunction.price(subRoot.getCore(), activeColor, gameState);
            subRoot.getCore().setPrice(value);
            return value;
        }

        final Modeling modeling = new Modeling(subRoot.getCore(), currentColor);

        final ExecutorService executorService = Executors.newFixedThreadPool(modeling.getCount());

        if (isMax) {

            subRoot.getCore().setPrice(Integer.MIN_VALUE);

            while (modeling.hasNext()) {

                    final Pair<Position, IAnswer> pair = modeling.next();

                executorService.execute(() -> {

                    final INode child = new Node(pair.getFirst());
                    final IAnswer answer = pair.getSecond();

                    final IEdge edge = new Edge(child, answer);
                    subRoot.addEdge(edge);

                    double value = getSubTree(child, depth + 1);
                    value = Math.max(subRoot.getCore().getPrice(), value);

                    subRoot.getCore().setPrice(value);
                });
            }
            executorService.shutdown();
            try {
                executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
            } catch (final InterruptedException e) {
                e.printStackTrace();
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
