package neointernship.chess.client.communication.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FigureDeserializer /*extends KeyDeserializer*/ {
    ObjectMapper mapper = new ObjectMapper();

    /*@Override
    public Object deserializeKey(String figure, DeserializationContext deserializationContext) throws IOException {
        return mapper.readValue(figure, Figure.class);
    }*/
}
