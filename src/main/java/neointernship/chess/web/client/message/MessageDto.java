package neointernship.chess.web.client.message;

import neointernship.chess.web.client.message.data.Data;

public class MessageDto {
    private MessageCode messageCode;
    private Data data;

    public boolean validateMessageCode() throws Exception {
        if (messageCode == null) {
            throw new Exception("Код сообщения отсутствует");
        }

        if (data == null){
            throw new Exception("Данные отсутствуют");
        }

        return true;
    }

    public MessageCode getMessageCode() {
        return messageCode;
    }

    public Data getData() {
        return data;
    }
}
