package neointernship.chess.client.message;

import java.util.HashMap;

public class MessageDto {
    private MessageCode messageCode;
    private String mes;
    private HashMap<Integer[], String> figureMap;

    public boolean validateMessageCode() throws Exception {
        if (messageCode == null) {
            throw new Exception("Код сообщения отсутствует");
        }
        return true;
    }

    public MessageCode getMessageCode() {
        return messageCode;
    }

    public String getMes() {
        return mes;
    }

    public HashMap<Integer[], String> getFigureMap() {
        return figureMap;
    }
}
