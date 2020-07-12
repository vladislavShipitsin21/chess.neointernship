package neointernship.chess.game.gameplay.gamestate.controller;

import neointernship.chess.game.gameplay.gamestate.state.GameState;
import neointernship.chess.game.gameplay.gamestate.update.GameStateDefineLogic;
import neointernship.chess.game.gameplay.gamestate.update.KingHasMovesComputation;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.enums.KingState;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.subscriber.ISubscriber;

public class GameStateController implements ISubscriber, IGameStateController {
    private GameState currentState;

    private final KingHasMovesComputation kingHasMovesComputation;
    private final GameStateDefineLogic gameStateDefineLogic;

    public GameStateController(final IPossibleActionList possibleActionList,
                                final IMediator mediator) {

        currentState = new GameState(EnumGameState.ALIVE, Color.BOTH);
        kingHasMovesComputation = new KingHasMovesComputation(possibleActionList, mediator);
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
        boolean kingHasMoves = kingHasMovesComputation.kingHasMoves(color);
        System.out.format("%s King has moves: %b\n", color, kingHasMoves);

        currentState = new GameState(gameStateDefineLogic.getState(kingState, kingHasMoves), color);

        System.out.format("Game status updated: %s\n", currentState.getValue());
    }
}

