package neointernship.bots.functionsH.bonus;

import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.enums.Color;

public interface IBonus {
    double getBonus(final Position position,final Color playerColor);
}
