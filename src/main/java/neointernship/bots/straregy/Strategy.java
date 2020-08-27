package neointernship.bots.straregy;

import neointernship.chess.game.model.enums.Color;

public abstract class Strategy implements IStrategy {

    private final Color color;

    public Strategy(final Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
