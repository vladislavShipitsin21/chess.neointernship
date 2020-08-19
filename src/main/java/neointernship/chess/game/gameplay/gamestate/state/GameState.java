package neointernship.chess.game.gameplay.gamestate.state;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;

public class GameState implements IGameState {
    private EnumGameState value;
    private Color color;

    public GameState(EnumGameState value, Color color) {
        this.value = value;
        this.color = color;
    }

    @Override
    public EnumGameState getValue() {
        return value;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void updateValue(EnumGameState value, Color color) {
        this.value = value;
        this.color = color;
    }
}
