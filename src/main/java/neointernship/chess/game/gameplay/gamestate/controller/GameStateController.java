package neointernship.chess.game.gameplay.gamestate.controller;

import neointernship.chess.game.gameplay.gamestate.update.GameStateDefineLogic;
import neointernship.chess.game.gameplay.gamestate.update.KingHasMovesComputation;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.GameState;
import neointernship.chess.game.model.enums.KingState;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.mediator.IMediator;

public class GameStateController implements IGameStateController {
    private GameState currentState;

    private final KingHasMovesComputation kingHasMovesComputation;
    private final GameStateDefineLogic gameStateDefineLogic;

    public GameStateController(final IPossibleActionList possibleActionList,
                                final IMediator mediator) {
        currentState = GameState.ALIVE;

        kingHasMovesComputation = new KingHasMovesComputation(possibleActionList, mediator);
        gameStateDefineLogic = new GameStateDefineLogic();
    }

    public void update(final Color color, final KingState kingState) {
        boolean kingHasMoves = kingHasMovesComputation.kingHasMoves(color);

        currentState = gameStateDefineLogic.getState(kingState, kingHasMoves);
    }

    public GameState getState(final Color color) {
        return currentState;
    }

    @Override
    public boolean isMatchAlive() {
        return currentState == GameState.ALIVE;
    }
}
