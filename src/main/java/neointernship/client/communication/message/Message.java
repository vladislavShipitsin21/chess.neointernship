package neointernship.client.communication.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message implements IMessage{
    private final MessageCode messageCode;

    public Message(@JsonProperty("messageCode") final MessageCode messageCode) {
        this.messageCode = messageCode;
    }

    public MessageCode getMessageCode() {
        return messageCode;
    }
}
