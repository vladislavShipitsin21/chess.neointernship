package neointernship.chess.game.gameplay.kingstate.controller;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.KingState;

public interface IKingStateController {
    void updateState(final Color color);
    KingState getState(final Color color);
}
