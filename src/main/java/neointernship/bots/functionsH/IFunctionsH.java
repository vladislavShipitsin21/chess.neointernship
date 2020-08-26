package neointernship.bots.functionsH;

import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.model.enums.Color;

public interface IFunctionsH {
    // в идеале не должно быть привязки к цвету
    double price(final Position position, final Color color, final IGameState gameState);
}
