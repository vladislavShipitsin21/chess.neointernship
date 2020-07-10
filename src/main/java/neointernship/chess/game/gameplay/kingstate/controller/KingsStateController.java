package neointernship.chess.game.gameplay.kingstate.controller;

import neointernship.chess.game.gameplay.kingstate.update.KingIsAttackedComputation;
import neointernship.chess.game.gameplay.kingstate.update.KingStateDefineLogic;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.KingState;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.subscriber.ISubscriber;

import java.util.ArrayList;
import java.util.HashMap;

public class KingsStateController implements IKingStateController {
    private final ArrayList<ISubscriber> subscribersList;

    private final HashMap<Color, KingState> kingStateMap;
    private final KingIsAttackedComputation kingIsAttackedComputation;
    private final KingStateDefineLogic kingStateDefineLogic;

    private IPlayer activePlayer;

    public KingsStateController(final IPossibleActionList possibleActionList,
                                final IMediator mediator,
                                final IPlayer activePlayer) {
        kingStateMap = new HashMap<Color, KingState>() {{
            put(Color.WHITE, KingState.FREE);
            put(Color.BLACK, KingState.FREE);
        }};

        this.activePlayer = activePlayer;

        subscribersList = new ArrayList<>();

        kingIsAttackedComputation = new KingIsAttackedComputation(possibleActionList, mediator);
        kingStateDefineLogic = new KingStateDefineLogic();
    }

    public void updateState() {
        boolean kingIsAttacked = kingIsAttackedComputation.kingIsAttacked(activePlayer.getColor());

        KingState newState = kingStateDefineLogic.getState(kingIsAttacked);

        if (newState != kingStateMap.get(activePlayer.getColor())) {
            for (ISubscriber currentSubscriber : subscribersList) {
                currentSubscriber.update(activePlayer.getColor(), newState);
            }

            kingStateMap.replace(activePlayer.getColor(), newState);
        }
    }

    public KingState getState() {
        return kingStateMap.get(activePlayer.getColor());
    }

    public void addToSubscriber(ISubscriber subscriber) {
        subscribersList.add(subscriber);
    }
}
