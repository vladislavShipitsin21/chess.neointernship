package neointernship.client.communication.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import neointernship.client.communication.data.initgame.IInitGame;
import neointernship.client.communication.data.initgame.InitGameDto;

public class SerializerForInitGame {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private SerializerForInitGame(){}

    public static String serializer(final IInitGame initGame) throws JsonProcessingException {
        return objectMapper.writeValueAsString(initGame);
    }

    public static InitGameDto deserializer(final String string) throws JsonProcessingException {
        return objectMapper.readValue(string, InitGameDto.class);
    }
}
