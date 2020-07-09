package neointernship.chess.game.gameplay.kingstatecontroller;

import neointernship.chess.game.gameplay.kingstatecontroller.states.IKingState;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.Figure;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.mediator.IMediator;

import java.util.HashMap;

public class KingsStateController {
    private final Figure whiteKing;
    private final Figure blackKing;

    private final IPossibleActionList possibleActionList;
    private final IMediator mediator;

    private final HashMap<Color, IKingState> kingStateMap;

    public KingsStateController(final Figure whiteKing,
                                final Figure blackKing,
                                final IPossibleActionList possibleActionList,
                                final IMediator mediator) {
        this.whiteKing = whiteKing;
        this.blackKing = blackKing;

        this.possibleActionList = possibleActionList;
        this.mediator = mediator;

        kingStateMap = new HashMap<>();
    }

    public void updateState(final Color color) {
        IKingState currentState = kingStateMap.get(color);
        currentState.update(possibleActionList, mediator);
    }
}
