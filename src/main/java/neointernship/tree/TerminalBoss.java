package neointernship.tree;

import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.GameStateController;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.story.IStoryGame;

public class TerminalBoss {

    public static IGameState getStatePosition(final Position position, final Color activeColor) {
        final PossibleActionList possibleActionList = position.getPossibleActionList();
        final IMediator mediator = position.getMediator();
        final IStoryGame storyGame = possibleActionList.getStoryGame();

        final GameStateController gameStateController =
                new GameStateController(possibleActionList, mediator, storyGame);

        gameStateController.updateWithoutUpdateList(activeColor);

        return gameStateController.getState();
    }

    public static boolean isTerminal(final IGameState gameState) {
        return gameState.getValue() != EnumGameState.ALIVE;
    }

    public static boolean isTerminal(final Position position, final Color activeColor) {
        final PossibleActionList possibleActionList = position.getPossibleActionList();
        final IMediator mediator = position.getMediator();
        final IStoryGame storyGame = possibleActionList.getStoryGame();

        final GameStateController gameStateController =
                new GameStateController(possibleActionList, mediator, storyGame);

        gameStateController.updateWithoutUpdateList(activeColor);

        return !gameStateController.isMatchAlive();
    }

}
