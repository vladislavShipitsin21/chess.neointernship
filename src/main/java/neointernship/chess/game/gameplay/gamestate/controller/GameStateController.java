package neointernship.chess.game.gameplay.gamestate.controller;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.gamestate.state.GameState;
import neointernship.chess.game.gameplay.gamestate.update.FiguresHaveMovesComputation;
import neointernship.chess.game.gameplay.gamestate.update.GameStateDefineLogic;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.enums.KingState;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.subscriber.ISubscriber;

public class GameStateController implements ISubscriber, IGameStateController {
    private GameState currentState;

    private final FiguresHaveMovesComputation figuresHaveMovesComputation;
    private final GameStateDefineLogic gameStateDefineLogic;

    public GameStateController(final IPossibleActionList possibleActionList,
                                final IMediator mediator) {

        currentState = new GameState(EnumGameState.ALIVE, Color.BOTH);
        figuresHaveMovesComputation = new FiguresHaveMovesComputation(possibleActionList, mediator);
        gameStateDefineLogic = new GameStateDefineLogic();
    }


    public GameState getState() {
        return currentState;
    }

    @Override
    public boolean isMatchAlive() {
        return currentState.getValue() == EnumGameState.ALIVE;
    }

    @Override
    public void update(Color color, KingState kingState) {
        boolean figuresHaveMoves = figuresHaveMovesComputation.check(color);
        currentState = new GameState(gameStateDefineLogic.getState(kingState, figuresHaveMoves), color);

        System.out.format("Game status updated: %s\n", currentState.getValue());
    }
}

