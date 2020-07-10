package neointernship.chess.client.message;

import java.util.HashMap;

public class Message {
    private final MessageCode messageCode;
    private final String mes;
    private final HashMap<Integer[], String> figureMap;

    public Message(final MessageCode messageCode, final String mes, final HashMap<Integer[], String> figureMap) {
        this.messageCode = messageCode;
        this.mes = mes;
        this.figureMap = figureMap;
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
