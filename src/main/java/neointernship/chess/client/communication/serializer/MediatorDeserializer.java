package neointernship.chess.client.communication.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.field.IField;

import java.io.IOException;
import java.util.HashMap;

public class MediatorDeserializer extends JsonDeserializer<Mediator> {
    ObjectMapper objectMapper = new ObjectMapper();
    HashMap<IField, Figure> mediator;

    @Override
    public Mediator deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        mediator = objectMapper.readValue(jsonParser, HashMap.class);
        return new Mediator(mediator);
    }
}
