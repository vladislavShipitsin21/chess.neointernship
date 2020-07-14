package neointernship.chess.client.message;

import neointernship.chess.client.message.data.Data;

public class Message<T> {
    private final MessageCode messageCode;
    private final Data<T> data;

    public Message(final MessageCode messageCode, final Data<T> data) {
        this.messageCode = messageCode;
        this.data = data;
    }

    public MessageCode getMessageCode() {
        return messageCode;
    }

    public Data<T> getData() {
        return data;
    }
}
