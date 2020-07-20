package neointernship.chess.game.gameplay.activeplayercontroller;

import neointernship.chess.game.model.player.IPlayer;

public interface IActivePlayerController {
    void update();
    IPlayer getCurrentPlayer();
}
