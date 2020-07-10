package neointernship.chess.game.gameplay.gamestate.controller;

import neointernship.chess.game.gameplay.gamestate.state.GameState;
import neointernship.chess.game.gameplay.gamestate.update.GameStateDefineLogic;
import neointernship.chess.game.gameplay.gamestate.update.KingHasMovesComputation;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.enums.KingState;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.mediator.IMediator;

public class GameStateController implements IGameStateController {
    private GameState currentState;

    private final KingHasMovesComputation kingHasMovesComputation;
    private final GameStateDefineLogic gameStateDefineLogic;

    public GameStateController(final IPossibleActionList possibleActionList,
                                final IMediator mediator) {

        currentState = new GameState(EnumGameState.ALIVE, Color.BOTH);
        kingHasMovesComputation = new KingHasMovesComputation(possibleActionList, mediator);
        gameStateDefineLogic = new GameStateDefineLogic();
    }

    public void update(final Color color, final KingState kingState) {
        boolean kingHasMoves = kingHasMovesComputation.kingHasMoves(color);

        currentState = new GameState(gameStateDefineLogic.getState(kingState, kingHasMoves), color);
    }

    public GameState getState() {
        return currentState;
    }

    @Override
    public boolean isMatchAlive() {
        return currentState.getValue() == EnumGameState.ALIVE;
    }
}

