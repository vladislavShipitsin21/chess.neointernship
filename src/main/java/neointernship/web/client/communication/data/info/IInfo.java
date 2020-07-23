package neointernship.web.client.communication.data.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import neointernship.chess.game.model.enums.Color;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Info.class, name = "Name"),
        @JsonSubTypes.Type(value = InfoDto.class, name = "NameDto"),
})
public interface IInfo {
    String getName();
    Color getColor();
}
