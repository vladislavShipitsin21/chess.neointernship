package neointernship.chess.game.gameplay.activecolorcontroller;

import neointernship.chess.game.model.enums.Color;

public interface IActiveColorController {
    void update();
    Color getCurrentColor();
}
