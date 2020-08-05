package neointernship.bots.functionsH;

import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;

import java.util.function.Function;

public class TargetFunction {

    /*public static Function<Position, Double> price = position -> {
        double result = 0;
        for (final Figure figure : position.getMediator().getFigures()) {
            if (figure.getClass() != King.class) {
                if (position.getActiveColor() == figure.getColor()) {
                    result += figure.getPrice();
                } else {
                    result -= figure.getPrice();
                }
            }
        }
        return result;
    };*/

    /**
     * оценивает текущую позицию
     * @param position позиция
     * @param activeColor цвет игрока, для которого необходимо оценить позицию
     * @return
     */
    public static double price (final Position position,final Color activeColor) {
        double result = 0;
        for (final Figure figure : position.getMediator().getFigures()) {
            if (figure.getClass() != King.class) {
                if (activeColor == figure.getColor()) {
                    result += figure.getPrice();
                } else {
                    result -= figure.getPrice();
                }
            }
        }
        return result;
    }

}
