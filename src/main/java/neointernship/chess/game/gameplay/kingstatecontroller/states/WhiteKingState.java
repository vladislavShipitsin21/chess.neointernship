package neointernship.chess.game.gameplay.kingstatecontroller.states;

import neointernship.chess.game.gameplay.kingstatecontroller.stateupdater.KingStateDefiner;
import neointernship.chess.game.model.enums.KingState;
import neointernship.chess.game.model.figure.Figure;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.mediator.IMediator;

public class WhiteKingState implements IKingState {
    private KingState state;
    private Figure king;

    public WhiteKingState(final Figure king) {
        state = KingState.FREE;
        this.king = king;
    }

    @Override
    public KingState getState() {
        return state;
    }

    @Override
    public void update(final IPossibleActionList possibleActionList, final IMediator mediator) {
        state = KingStateDefiner.define(king, possibleActionList, mediator);
    }
}
