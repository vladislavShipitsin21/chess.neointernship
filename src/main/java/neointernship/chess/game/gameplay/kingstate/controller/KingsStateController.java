package neointernship.chess.game.gameplay.kingstate.controller;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.kingstate.update.KingIsAttackedComputation;
import neointernship.chess.game.gameplay.kingstate.update.KingStateDefineLogic;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.KingState;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.subscriber.ISubscriberKing;

import java.util.ArrayList;
import java.util.HashMap;

public class KingsStateController implements IKingStateController {
    private final ArrayList<ISubscriberKing> subscribersList;

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


        subscribersList = new ArrayList<>();

        kingIsAttackedComputation = new KingIsAttackedComputation(possibleActionList, mediator);
        kingStateDefineLogic = new KingStateDefineLogic();
    }

    public void updateState() {
        boolean kingIsAttacked = kingIsAttackedComputation.kingIsAttacked(activePlayer.getColor());

        KingState newState = kingStateDefineLogic.getState(kingIsAttacked);
        System.out.println(activePlayer.getColor() + " KING STATUS UPDATED: " + newState.toString());

        if (newState != kingStateMap.get(activePlayer.getColor())) {
            for (ISubscriberKing currentSubscriber : subscribersList) {
                currentSubscriber.update(activePlayer.getColor(), newState);
            }

            kingStateMap.replace(activePlayer.getColor(), newState);
        }
    }

    @Override
    public KingState getState() {
        return kingStateMap.get(activePlayer.getColor());
    }

    @Override
    public void setActivePlayer(final IPlayer activePlayer) {
        this.activePlayer = activePlayer;
    }

    @Override
    public void addToSubscriber(ISubscriberKing subscriber) {
        subscribersList.add(subscriber);
    }
}
