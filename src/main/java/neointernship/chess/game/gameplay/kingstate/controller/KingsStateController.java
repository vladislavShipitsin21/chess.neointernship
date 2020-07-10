package neointernship.chess.game.gameplay.kingstate.controller;

import neointernship.chess.game.gameplay.kingstate.update.KingIsAttackedComputation;
import neointernship.chess.game.gameplay.kingstate.update.KingStateDefineLogic;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.KingState;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.mediator.IMediator;

import java.util.HashMap;

public class KingsStateController implements IKingStateController {
    private final HashMap<Color, KingState> kingStateMap;
    private final KingIsAttackedComputation kingIsAttackedComputation;
    private final KingStateDefineLogic kingStateDefineLogic;

    public KingsStateController(final IPossibleActionList possibleActionList,
                                final IMediator mediator) {
        kingStateMap = new HashMap<Color, KingState>() {{
            put(Color.WHITE, KingState.FREE);
            put(Color.BLACK, KingState.FREE);
        }};

        kingIsAttackedComputation = new KingIsAttackedComputation(possibleActionList, mediator);
        kingStateDefineLogic = new KingStateDefineLogic();
    }

    public void updateState(final Color color) {
        boolean kingIsAttacked = kingIsAttackedComputation.kingIsAttacked(color);

        KingState newState = kingStateDefineLogic.getState(kingIsAttacked);

        if (newState != kingStateMap.get(color)) {
            // update subscriber
            kingStateMap.replace(color, newState);
        }
    }

    public KingState getState(final Color color) {
        return kingStateMap.get(color);
    }
}
