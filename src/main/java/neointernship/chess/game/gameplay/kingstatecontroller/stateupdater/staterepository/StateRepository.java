package neointernship.chess.game.gameplay.kingstatecontroller.stateupdater.staterepository;

import neointernship.chess.game.model.enums.KingState;
import neointernship.chess.game.model.util.Pair;

import java.util.HashMap;

public class StateRepository {
    private final HashMap<Pair<Boolean, Boolean>, KingState> stateRepository;

    public StateRepository() {
        stateRepository = new HashMap<Pair<Boolean, Boolean>, KingState>() {
            {
                put(new Pair<>(true, true), KingState.CHECK);
                put(new Pair<>(true, false), KingState.MATE);
                put(new Pair<>(false, true), KingState.STALEMATE);
            }
        };
    }

    public KingState getState(final Boolean isKingAttacked, final Boolean isKingHasMoves) {
        return stateRepository.getOrDefault(new Pair<>(isKingAttacked, isKingHasMoves), KingState.FREE);
    }
}
