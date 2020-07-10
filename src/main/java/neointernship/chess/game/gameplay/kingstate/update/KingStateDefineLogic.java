package neointernship.chess.game.gameplay.kingstate.update;

import neointernship.chess.game.model.enums.KingState;

import java.util.HashMap;

public class KingStateDefineLogic {
    private final HashMap<Boolean, KingState> stateRepository;

    public KingStateDefineLogic() {
        stateRepository = new HashMap<Boolean, KingState>() {
            {
                put(true, KingState.CHECK);
            }
        };
    }

    public KingState getState(final Boolean isKingAttacked) {
        return stateRepository.getOrDefault(isKingAttacked, KingState.FREE);
    }
}
