package neointernship.chess.game.gameplay.kingstate.controller;

import neointernship.chess.game.model.enums.KingState;

public interface IKingStateController {
    void updateState();
    KingState getState();
}
