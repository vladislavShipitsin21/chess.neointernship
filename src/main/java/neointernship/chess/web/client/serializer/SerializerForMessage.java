package neointernship.chess.web.client.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import neointernship.chess.web.client.message.Message;
import neointernship.chess.web.client.message.MessageDto;

public final class SerializerForMessage {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Class aClass = MessageDto.class;

    private SerializerForMessage(){}

    public static String serializer(final Message message) throws JsonProcessingException {
        return objectMapper.writeValueAsString(message);
    }

    public static MessageDto deserializer(final String string) throws JsonProcessingException {
        return (MessageDto) objectMapper.readValue(string, aClass);
    }
}
