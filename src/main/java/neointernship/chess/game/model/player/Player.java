package neointernship.chess.game.model.player;

import neointernship.chess.game.model.enums.Color;

public abstract class Player implements IPlayer {
    private Color color;
    private final String name;

    public Player(final String name, final Color color) {
        this.color = color;
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(final Color color) {
        this.color = color;
    }

    public String getName() {
        return name ;
    }

}
