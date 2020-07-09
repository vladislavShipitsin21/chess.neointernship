package neointernship.chess.game.gameplay.kingstatecontroller.states;

import neointernship.chess.game.model.enums.KingState;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.mediator.IMediator;

public interface IKingState {
    KingState getState();
    void update(final IPossibleActionList possibleActionList, final IMediator mediator);
}
