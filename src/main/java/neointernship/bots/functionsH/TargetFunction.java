package neointernship.bots.functionsH;

import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.GameStateController;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.story.IStoryGame;

import static neointernship.bots.modeling.Progressing.isTerminal;

public class TargetFunction {

    /**
     * оценивает текущую позицию
     * @param position позиция
     * @param playerColor цвет игрока, для которого необходимо оценить позицию
     * @return
     */
    public static double price (final Position position,final Color playerColor,final IGameState gameState) {

        if(isTerminal(gameState)) {
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
        return result;
    }

    public static double price (final Position position,final Color playerColor,final Color currentColor) {

        PossibleActionList possibleActionList = position.getPossibleActionList();
        IMediator mediator = position.getMediator();
        IStoryGame storyGame = possibleActionList.getStoryGame();
        GameStateController gameStateController =
                new GameStateController(possibleActionList, mediator, storyGame);

        gameStateController.updateWithoutUpdateList(currentColor);

        IGameState gameState =  gameStateController.getState();

        if(isTerminal(gameState)) {
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
        return result;
    }

}
