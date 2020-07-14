package neointernship.chess.game.gameplay.kingstate.controller;

import neointernship.chess.game.model.enums.KingState;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.subscriber.ISubscriber;

public interface IKingStateController {
    void updateState();
    KingState getState();
    void setActivePlayer(final IPlayer activePlayer);
    void addToSubscriber(ISubscriber subscriber);
}
