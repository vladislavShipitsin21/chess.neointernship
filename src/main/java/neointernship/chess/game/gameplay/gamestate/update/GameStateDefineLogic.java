package neointernship.chess.game.gameplay.gamestate.update;

import neointernship.chess.game.model.enums.GameState;
import neointernship.chess.game.model.enums.KingState;
import neointernship.chess.game.model.util.Pair;

import java.util.HashMap;

public class GameStateDefineLogic {
    private final HashMap<Pair<KingState, Boolean>, GameState> stateRepository;

    public GameStateDefineLogic() {
        stateRepository = new HashMap<Pair<KingState, Boolean>, GameState>() {
            {
                put(new Pair<>(KingState.CHECK, false), GameState.MATE);
                put(new Pair<>(KingState.FREE, false), GameState.STALEMATE);
            }
        };
    }

    public GameState getState(final KingState kingState, final Boolean kingHasMoves) {
        return stateRepository.getOrDefault(new Pair<>(kingState, kingHasMoves), GameState.ALIVE);
    }
}
