package neointernship.client.communication.message;


import neointernship.client.communication.message.reaction.view.IMessageCode;
import neointernship.client.communication.message.reaction.view.MessageCodeInitGame;
import neointernship.client.communication.message.reaction.view.MessageCodeTurn;
import neointernship.client.communication.message.reaction.view.MessageCodeUpdate;

import java.util.HashMap;

public class MessageReactionForView {
    private final HashMap<MessageCode, IMessageCode> messageReaction;

    public MessageReactionForView() {
        this.messageReaction = new HashMap<>();
        initMessageReaction(messageReaction);
    }

    private void initMessageReaction(final HashMap<MessageCode, IMessageCode> messageReaction) {
        //НУЖНО ОПРЕДЕЛИТЬСЯ С КЛАССАМИ И/ИЛИ ВОССТАНОВИТЬ ЭТИ КЛАССЫ
        /*messageReaction.put(MessageCode.CONNECT, new MessageCodeConnect());
        messageReaction.put(MessageCode.DRAW, new MessageCodeDraw());
        messageReaction.put(MessageCode.ERROR, new MessageCodeError());
        messageReaction.put(MessageCode.LOSE, new MessageCodeLose());
        messageReaction.put(MessageCode.MOVE_FIGURE, new MessageCodeMoveFigure());
        messageReaction.put(MessageCode.OK, new MessageCodeOk());
        messageReaction.put(MessageCode.PICK_FIGURE, new MessageCodePickFigure());
        messageReaction.put(MessageCode.WIN, new MessageCodeWin());*/
        messageReaction.put(MessageCode.TURN, new MessageCodeTurn());
        messageReaction.put(MessageCode.INIT_GAME, new MessageCodeInitGame());
        messageReaction.put(MessageCode.UPDATE, new MessageCodeUpdate());
    }

    public IMessageCode get(final MessageCode messageCode) {
        return messageReaction.get(messageCode);
    }
}
