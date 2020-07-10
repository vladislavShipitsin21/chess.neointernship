package neointernship.chess.game.gameplay.kingstate.states;

import neointernship.chess.game.gameplay.kingstate.stateupdater.KingStateDefiner;
import neointernship.chess.game.model.enums.KingState;
import neointernship.chess.game.model.figure.Figure;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;

public class BlackKingState implements IKingState {
    private KingState state;
    private Figure king;

    public BlackKingState(final Figure king) {
        state = KingState.FREE;
        this.king = king;
    }

    @Override
    public KingState getState() {
        return state;
    }

    @Override
    public void update(final IPossibleActionList possibleActionList) {
        state = KingStateDefiner.define(king, possibleActionList);
    }
}
