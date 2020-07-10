package neointernship.chess.game.gameplay.kingstate.controller;

import neointernship.chess.game.gameplay.kingstate.states.IKingState;
import neointernship.chess.game.gameplay.kingstate.stateupdater.staterepository.KingStateRepository;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.KingState;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;

import java.util.HashMap;

public class KingsStateController implements IKingStateController {
    private final Figure whiteKingState;
    private final Figure blackKingState;

    private final IPossibleActionList possibleActionList;
    private final IMediator mediator;

    private final KingStateRepository kingStateMap;

    public KingsStateController(final IPossibleActionList possibleActionList,
                                final IMediator mediator) {
        this.whiteKing = mediator.getWhiteKing();
        this.blackKing = mediator.getBlackKing();

        this.possibleActionList = possibleActionList;
        this.mediator = mediator;

        kingStateMap = new KingStateRepository();
    }

    public void updateState() {
        IKingState currentState = kingStateMap.get(color);
        currentState.update(possibleActionList, mediator);
    }

    public KingState getState() {
        return kingStateMap.getState(color).getState();
    }
}
