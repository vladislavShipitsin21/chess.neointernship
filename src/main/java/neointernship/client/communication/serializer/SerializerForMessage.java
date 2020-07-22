package neointernship.client.communication.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import neointernship.client.communication.message.IMessage;
import neointernship.client.communication.message.MessageDto;

public final class SerializerForMessage {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private SerializerForMessage(){}

    public static String serializer(final IMessage message) throws JsonProcessingException {
        return objectMapper.writeValueAsString(message);
    }

    public static MessageDto deserializer(final String string) throws JsonProcessingException {
        return objectMapper.readValue(string, MessageDto.class);
    }
}
