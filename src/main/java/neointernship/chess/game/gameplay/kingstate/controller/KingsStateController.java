package neointernship.chess.game.gameplay.kingstate.controller;

import neointernship.chess.game.gameplay.kingstate.states.IKingState;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.KingState;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;

import java.util.HashMap;

public class KingsStateController implements IKingStateController {
    private final Figure whiteKing;
    private final Figure blackKing;

    private final IPossibleActionList possibleActionList;
    private final IMediator mediator;

    private final HashMap<Color, IKingState> kingStateMap;

    public KingsStateController(final IPossibleActionList possibleActionList,
                                final IMediator mediator) {
        this.whiteKing = mediator.getWhiteKing();
        this.blackKing = mediator.getBlackKing();

        this.possibleActionList = possibleActionList;
        this.mediator = mediator;

        kingStateMap = new HashMap<>();
    }

    public void updateState(final Color color) {
        IKingState currentState = kingStateMap.get(color);
        currentState.update(possibleActionList, mediator);
    }

    public KingState getState(final Color color) {
        return kingStateMap.get(color).getState();
    }
}
