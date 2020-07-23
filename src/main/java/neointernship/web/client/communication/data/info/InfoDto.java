package neointernship.web.client.communication.data.info;

import neointernship.chess.game.model.enums.Color;

public class InfoDto implements IInfo {
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
