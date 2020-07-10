package neointernship.chess.game.gameplay.kingstate.stateupdater.staterepository;

import neointernship.chess.game.model.enums.KingState;
import neointernship.chess.game.model.util.Pair;

import java.util.HashMap;

public class KingStateRepository {
    private final HashMap<Pair<Boolean, Boolean>, KingState> stateRepository;

    public KingStateRepository() {
        stateRepository = new HashMap<Pair<Boolean, Boolean>, KingState>() {
            {
                put(new Pair<>(true, true), KingState.CHECK);
            }
        };
    }

    public KingState getState(final Boolean isKingAttacked, final Boolean isKingHasMoves) {
        return stateRepository.getOrDefault(new Pair<>(isKingAttacked, isKingHasMoves), KingState.FREE);
    }
}
