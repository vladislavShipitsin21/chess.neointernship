package neointernship.web.client.communication.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import neointernship.web.client.communication.data.info.IInfo;
import neointernship.web.client.communication.data.info.InfoDto;

public class SerializerForInfo {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private SerializerForInfo(){}

    public static String serializer(final IInfo name) throws JsonProcessingException {
        return objectMapper.writeValueAsString(name);
    }

    public static InfoDto deserializer(final String string) throws JsonProcessingException {
        return objectMapper.readValue(string, InfoDto.class);
    }
}
