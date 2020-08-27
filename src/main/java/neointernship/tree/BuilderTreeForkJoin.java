package neointernship.tree;

import neointernship.bots.functionsH.TargetFunction;
import neointernship.bots.modeling.Modeling;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import static neointernship.chess.game.model.enums.Color.swapColor;

public class BuilderTreeForkJoin implements IBuilderTree {
    private final int max_depth;
    private final Color activeColor;

    public BuilderTreeForkJoin(final int max_depth, final Color activeColor) {
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
        new ForkJoinPool().invoke(new Task(root, 0));

        return root;
    }


    private boolean isEndTree(final int depth, final IGameState gameState) {
        return depth >= max_depth || TerminalBoss.isTerminal(gameState);
    }

    private class Task extends RecursiveTask<Double> {

        private final INode subRoot;
        private final int depth;

        public Task(final INode subRoot, final int depth) {
            this.subRoot = subRoot;
            this.depth = depth;
        }

        @Override
        protected Double compute() {
            final boolean isMax = depth % 2 == 0;
            final Color currentColor = isMax ? activeColor : swapColor(activeColor);

            final IGameState gameState = TerminalBoss.getStatePosition(subRoot.getCore(), currentColor);

            if (isEndTree(depth, gameState)) {

                final double value = TargetFunction.price(subRoot.getCore(), activeColor, gameState);
                subRoot.getCore().setPrice(value);
                return value;
            }

            final Map<Position, IAnswer> modelingMap = Modeling.modeling(subRoot.getCore(), currentColor);
            final List<Task> listTask = new LinkedList<>();

            if (isMax) {

                subRoot.getCore().setPrice(Integer.MIN_VALUE);

                for (final Map.Entry<Position, IAnswer> entry : modelingMap.entrySet()) {

                    final INode child = new Node(entry.getKey());
                    final IAnswer answer = entry.getValue();

                    final IEdge edge = new Edge(child, answer);
                    subRoot.addEdge(edge);

                    final Task task = new Task(child, depth + 1);
                    task.fork();
                    listTask.add(task);
                }
                for (int i = listTask.size() - 1; i >= 0  ; i--) {
                    double value = listTask.get(i).join();

                    value = Math.max(subRoot.getCore().getPrice(), value);

                    subRoot.getCore().setPrice(value);
                }

            } else {

                subRoot.getCore().setPrice(Integer.MAX_VALUE);

                for (final Map.Entry<Position, IAnswer> entry : modelingMap.entrySet()) {

                    final INode child = new Node(entry.getKey());
                    final IAnswer answer = entry.getValue();

                    final IEdge edge = new Edge(child, answer);
                    subRoot.addEdge(edge);

                    final Task task = new Task(child, depth + 1);
                    task.fork();
                    listTask.add(task);
                }
                for (int i = listTask.size() - 1; i >= 0  ; i--) {
                    double value = listTask.get(i).join();

                    value = Math.min(subRoot.getCore().getPrice(), value);

                    subRoot.getCore().setPrice(value);
                }
            }
            return subRoot.getCore().getPrice();
        }
    }
}


