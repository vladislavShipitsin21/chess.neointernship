package neointernship.web.client.communication.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import neointernship.web.client.communication.data.turn.ITurn;
import neointernship.web.client.communication.data.turn.TurnDto;

public class SerializerForTurn {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private SerializerForTurn(){}

    public static String serializer(final ITurn turn) throws JsonProcessingException {
        return objectMapper.writeValueAsString(turn);
    }

    public static TurnDto deserializer(final String string) throws JsonProcessingException {
        return objectMapper.readValue(string, TurnDto.class);
    }
}
