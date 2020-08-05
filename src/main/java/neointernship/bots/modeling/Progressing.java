package neointernship.bots.modeling;

import neointernship.chess.game.console.ConsoleBoardWriter;
import neointernship.chess.game.gameplay.activecolorcontroller.ActiveColorController;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.playmap.board.Board;

import java.util.HashSet;
import java.util.Set;

public class Progressing {

    private static int MAX_DEPTH = 0;

    public static void print(Position position,int depth){
        ConsoleBoardWriter printer = new ConsoleBoardWriter(position.getMediator(),new Board());
        System.out.println("глубина : " + depth);
        printer.printPosition(position);
    }

    // пока возвращает количесвто узлов для заданной глубины
    public static int progress(final Set<Position> positions,
                               int depth,
                               final ActiveColorController activeColorController,
                               final Set<Position> allPosition) {
        int count = 0;

        // достиг максимальной глубины
        if (depth >= MAX_DEPTH) {
            allPosition.addAll(positions);
            for (Position position : positions) {
                //print(position,depth);
            }
            return positions.size();
        }
        int startLabel = depth % 2 == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        final Color activeColor = depth % 2 == 0 ? Color.WHITE : Color.BLACK; // изменить для входного цвета

        depth++;

        for (Position position : positions) {
            position.setPrice(startLabel);

            //print(position,depth);

            final Set<Position> result = Modeling.modeling(position,activeColor).keySet();
//            final Map<Position, IAnswer> result = new HashMap<>(Modeling.modeling(position, activeColor));

            for (Position position1 : result) {
                //print(position1,depth);
            }
            count += progress(result,depth,activeColorController,allPosition);
        }
        return allPosition.size();
    }

    public static int progress(final Position startPosition,final int maxDepth){
        Set<Position> positions = new HashSet<>();
        Set<Position> allpositions = new HashSet<>();
        positions.add(startPosition);
        MAX_DEPTH = maxDepth;

        ActiveColorController activeColorController = new ActiveColorController();
        activeColorController.update();

        final int count = progress(positions,0,activeColorController,allpositions);

       // System.out.println("size positions : " + allpositions.size());

        return count;
    }

   /* public static IAnswer progress2(final Collection<Position> positions,
                                    int depth,
                                    final ActiveColorController activeColorController) {
        int count = 0;
        IAnswer answerResult = null;

        activeColorController.update();
        final Color activeColor = activeColorController.getCurrentColor();

        // достиг максимальной глубины
        if (depth >= MAX_DEPTH) {
            // оценка позиции
           // return positions.size();
        }

        int startLabel = depth % 2 == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        depth++;

        for (Position position : positions) {

            position.setPrice(startLabel);

            final Collection<Position> result = Modeling.modeling(position,activeColor).keySet();
            count += progress(result,depth,activeColorController);
        }
        return answerResult;
    }*/


}
