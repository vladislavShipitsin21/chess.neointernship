package neointernship.web.client.communication.data.info;

import neointernship.chess.game.model.enums.Color;

public class Info implements IInfo {
    private final String name;
    private final Color color;

    public Info(final String name, final Color color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
