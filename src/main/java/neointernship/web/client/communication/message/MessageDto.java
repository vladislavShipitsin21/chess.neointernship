package neointernship.web.client.communication.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDto {
    private MessageCode messageCode;

    public boolean validate() throws Exception {
        if (messageCode == null) {
            throw new Exception("Код сообщения отсутствует");
        }
        return true;
    }

    public MessageCode getMessageCode() {
        return messageCode;
    }
}
