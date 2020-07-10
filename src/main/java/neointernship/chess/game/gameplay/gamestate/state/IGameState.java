package neointernship.chess.game.gameplay.gamestate.state;

import neointernship.chess.game.model.enums.Color;

public interface IGameState {
    GameState getValue();
    void updateValue(GameState gameState, Color color);
}
