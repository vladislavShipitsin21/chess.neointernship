package neointernship.chess.client.message;

import neointernship.chess.client.message.reaction.*;

import java.util.HashMap;

public class MessageReaction {
    private HashMap<MessageCode, IMessageCode> messageReaction;

    public MessageReaction() {
        this.messageReaction = new HashMap<>();
        initMessageReaction(messageReaction);
    }

    private void initMessageReaction(final HashMap<MessageCode, IMessageCode> messageReaction) {
        //НУЖНО ОПРЕДЕЛИТЬСЯ С КЛАССАМИ И/ИЛИ ВОССТАНОВИТЬ ЭТИ КЛАССЫ
        /*messageReaction.put(MessageCode.CONNECT, new MessageCodeConnect());
        messageReaction.put(MessageCode.DRAW, new MessageCodeDraw());
        messageReaction.put(MessageCode.ERROR, new MessageCodeError());
        messageReaction.put(MessageCode.FIGURES_LIST, new MessageCodeFiguresList());
        messageReaction.put(MessageCode.LOSE, new MessageCodeLose());
        messageReaction.put(MessageCode.MOVE_FIGURE, new MessageCodeMoveFigure());
        messageReaction.put(MessageCode.OK, new MessageCodeOk());
        messageReaction.put(MessageCode.PAINT, new MessageCodePaint());
        messageReaction.put(MessageCode.PICK_FIGURE, new MessageCodePickFigure());
        messageReaction.put(MessageCode.WIN, new MessageCodeWin());*/
        messageReaction.put(MessageCode.TURN, new MessageCodeTurn());
    }

    public HashMap<MessageCode, IMessageCode> getMessageReaction() {
        return messageReaction;
    }

    public IMessageCode get(final MessageCode messageCode) {
        return messageReaction.get(messageCode);
    }
}
