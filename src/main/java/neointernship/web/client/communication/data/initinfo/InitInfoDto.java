package neointernship.web.client.communication.data.initinfo;

import neointernship.chess.game.model.enums.Color;

public class InitInfoDto implements IInitInfo {
    private String name;
    private Color color;

    public boolean validate() throws Exception {
        if (name == null) {
            throw new Exception("Отсутствует name");
        }
        if (color == null) {
            throw new Exception("Отсутствует color");
        }
        return true;
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
