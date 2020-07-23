package neointernship.web.client.communication.data.initinfo;

import neointernship.chess.game.model.enums.Color;

public class InitInfo implements IInitInfo {
    private final String name;
    private final Color color;

    public InitInfo(final String name, final Color color) {
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
