package neointernship.bots.functionsH;

import neointernship.bots.modeling.Progressing;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.GameStateController;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.gameplay.gamestate.state.GameState;
import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.story.IStoryGame;

import java.util.function.Function;

import static neointernship.bots.modeling.Progressing.isTerminal;

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
     * @param playerColor цвет игрока, для которого необходимо оценить позицию
     * @return
     */
    public static double price (final Position position,final Color playerColor,final Color currentColor,final IGameState gameState) {



        if(isTerminal(gameState)){
            if(gameState.getValue() == EnumGameState.MATE){
                return playerColor == gameState.getColor() ? -100 : 100;
            }
            return 0; // можно считать на сколько выгадна ничья
        }
        // наказание за ничью !
        double result = 0;
        for (final Figure figure : position.getMediator().getFigures()) {
            if (figure.getClass() != King.class) {
                if (playerColor == figure.getColor()) {
                    result += figure.getPrice();
                } else {
                    result -= figure.getPrice();
                }
            }
        }
        // добавить что-то про победу
        return result;
    }

}
