package neointernship.web.client.communication.data.initinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import neointernship.chess.game.model.enums.Color;

public class InitInfoDto implements IInitInfo {
    @JsonProperty
    private String name;
    @JsonProperty
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
