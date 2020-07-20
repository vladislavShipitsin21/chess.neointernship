package neointernship.chess.web.client.message;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import neointernship.chess.web.client.message.data.Data;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Message {
    private final MessageCode messageCode;
    private final Data data;

    public Message(final MessageCode messageCode, final Data data) {
        this.messageCode = messageCode;
        this.data = data;
    }

    public MessageCode getMessageCode() {
        return messageCode;
    }

    public Data getData() {
        return data;
    }
}
