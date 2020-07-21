package neointernship.chess.client.communication.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import neointernship.chess.client.communication.data.turn.ITurn;
import neointernship.chess.client.communication.data.turn.TurnDto;

public class SerializerForTurn {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Class aClass = ITurn.class;

    private SerializerForTurn(){}

    public static String serializer(final ITurn turn) throws JsonProcessingException {
        return objectMapper.writeValueAsString(turn);
    }

    public static TurnDto deserializer(final String string) throws JsonProcessingException {
        return (TurnDto) objectMapper.readValue(string, aClass);
    }
}
