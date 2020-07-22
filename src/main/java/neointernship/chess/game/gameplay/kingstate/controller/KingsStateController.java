package neointernship.chess.game.gameplay.kingstate.controller;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.kingstate.update.KingIsAttackedComputation;
import neointernship.chess.game.gameplay.kingstate.update.KingStateDefineLogic;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.KingState;
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

    private Color activeColor;

    public KingsStateController(final IPossibleActionList possibleActionList,
                                final IMediator mediator,
                                final Color activeColor) {
        kingStateMap = new HashMap<Color, KingState>() {{
            put(Color.WHITE, KingState.FREE);
            put(Color.BLACK, KingState.FREE);
        }};

        subscribersList = new ArrayList<>();

        kingIsAttackedComputation = new KingIsAttackedComputation(possibleActionList, mediator);
        kingStateDefineLogic = new KingStateDefineLogic();

        this.activeColor = activeColor;
    }

    public void updateState() {
        boolean kingIsAttacked = kingIsAttackedComputation.kingIsAttacked(activeColor);

        KingState newState = kingStateDefineLogic.getState(kingIsAttacked);
        System.out.println(activeColor + " KING STATUS UPDATED: " + newState.toString());

        if (newState != kingStateMap.get(activeColor)) {
            for (ISubscriber currentSubscriber : subscribersList) {
                currentSubscriber.update(activeColor, newState);
            }

            kingStateMap.replace(activeColor, newState);
        }
    }

    @Override
    public KingState getState() {
        return kingStateMap.get(activeColor);
    }

    @Override
    public void setActiveColor(final Color activeColor) {
        this.activeColor = activeColor;
    }

    @Override
    public void addToSubscriber(ISubscriber subscriber) {
        subscribersList.add(subscriber);
    }
}
