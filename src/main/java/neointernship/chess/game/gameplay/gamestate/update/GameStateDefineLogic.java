package neointernship.chess.game.gameplay.gamestate.update;

import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.enums.KingState;
import neointernship.chess.game.model.util.Pair;

import java.util.HashMap;

public class GameStateDefineLogic {
    private final HashMap<Pair<KingState, Boolean>, EnumGameState> stateRepository;

    public GameStateDefineLogic() {
        stateRepository = new HashMap<Pair<KingState, Boolean>, EnumGameState>() {
            {
                put(new Pair<>(KingState.CHECK, false), EnumGameState.MATE);
                put(new Pair<>(KingState.FREE, false), EnumGameState.STALEMATE);
            }
        };
    }

    public EnumGameState getState(final KingState kingState, final Boolean kingHasMoves) {
        return stateRepository.getOrDefault(new Pair<>(kingState, kingHasMoves), EnumGameState.ALIVE);
    }
}
