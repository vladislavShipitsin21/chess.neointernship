package neointernship.chess.client.communication.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import neointernship.chess.client.communication.data.initgame.IInitGame;
import neointernship.chess.client.communication.data.initgame.InitGame;
import neointernship.chess.client.communication.data.initgame.InitGameDto;

public class SerializerForInitGame {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Class aClass = InitGameDto.class;

    private SerializerForInitGame(){}

    public static String serializer(final IInitGame initGame) throws JsonProcessingException {
        return objectMapper.writeValueAsString(initGame);
    }

    public static InitGame deserializer(final String string) throws JsonProcessingException {
        return objectMapper.readValue(string, InitGame.class);
    }
}
