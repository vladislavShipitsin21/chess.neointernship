package neointernship.tree;

import neointernship.bots.functionsH.IFunctionsH;
import neointernship.bots.modeling.Modeling;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.*;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.util.Pair;

import java.util.HashMap;
import java.util.Map;

import static neointernship.chess.game.model.enums.Color.swapColor;

public class BuilderTreeWithBonus {

    private static final int MAX_COUNT_ACTIVE = 21000;
    private int max_depth;
    private final Color playerColor;
    private final Map<Class,Integer> figureMaxActive;

    private IFunctionsH function;

    public BuilderTreeWithBonus(final Color playerColor) {
        this.playerColor = playerColor;
        figureMaxActive = new HashMap<>();
        initMap();
    }
    private void initMap(){
       /* пешка - 4
        конь - 8
        король - 8
        слон - 13
        ладья - 14
        ферзь - 27*/
        figureMaxActive.put(Pawn.class,4);
        figureMaxActive.put(Knight.class,8);
        figureMaxActive.put(King.class,8);
        figureMaxActive.put(Bishop.class,13);
        figureMaxActive.put(Rook.class,14);
        figureMaxActive.put(Queen.class,27);
    }

    /**
     * возвращает ссылку на корень дерева
     *
     * @param startPosition стартовая позиция - корень
     * @return
     */
    public INode getTree(final Position startPosition, final IFunctionsH functionsH) {
        max_depth = getDepth(startPosition);

        function = functionsH;

        final long startTime = System.currentTimeMillis();
        INode root = new Node(startPosition);
        getSubTree(root, 0);
        final long finishTime = System.currentTimeMillis();

        if(Math.abs(finishTime - startTime) < 1000){
            System.out.println("пересчет с увелечением глубины 1!!!");
            root = new Node(startPosition);
            getSubTree(root, 0);
            max_depth++;
        }
        System.out.println("depth = " + max_depth);
        return root;
    }

    private int getDepth(final Position startPosition) {
        final IMediator mediator = startPosition.getMediator();

        int countActiveMy = 0;
        int countActiveOpponent = 0;

        for(final Figure figure : mediator.getFigures()){

            if(figure.getColor() == playerColor){
                countActiveMy += figureMaxActive.get(figure.getClass());
            }else{
                countActiveOpponent += figureMaxActive.get(figure.getClass());
            }
        }

        int countAllActive = countActiveMy * countActiveOpponent;
        int depth = 2;

        if(countAllActive > MAX_COUNT_ACTIVE){
            return depth;
        }

        countAllActive *= countActiveOpponent;

        if(countAllActive > MAX_COUNT_ACTIVE){
            return depth;
        }
        depth++;

        countAllActive *= countActiveMy;

        if(countAllActive > MAX_COUNT_ACTIVE){
            return depth;
        }

        depth++;

        return depth;
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
