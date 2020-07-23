package neointernship.web.client.communication.message;


import neointernship.web.client.communication.message.reaction.view.*;

import java.util.HashMap;

public class ViewMessageReaction {
    private final HashMap<ClientCodes, IMessageCodeView> messageReaction;

    public ViewMessageReaction() {
        this.messageReaction = new HashMap<>();
        initMessageReaction(messageReaction);
    }

    private void initMessageReaction(final HashMap<ClientCodes, IMessageCodeView> messageReaction) {
        //НУЖНО ОПРЕДЕЛИТЬСЯ С КЛАССАМИ И/ИЛИ ВОССТАНОВИТЬ ЭТИ КЛАССЫ
        /*messageReaction.put(MessageCode.CONNECT, new MessageCodeConnect());
        messageReaction.put(MessageCode.DRAW, new MessageCodeDraw());
        messageReaction.put(MessageCode.ERROR, new MessageCodeError());
        messageReaction.put(MessageCode.LOSE, new MessageCodeLose());
        messageReaction.put(MessageCode.MOVE_FIGURE, new MessageCodeMoveFigure());
        messageReaction.put(MessageCode.OK, new MessageCodeOk());
        messageReaction.put(MessageCode.PICK_FIGURE, new MessageCodePickFigure());
        messageReaction.put(MessageCode.WIN, new MessageCodeWin());*/
        messageReaction.put(ClientCodes.TURN, new TurnView());
        messageReaction.put(ClientCodes.INIT_GAME, new InitGameView());
        messageReaction.put(ClientCodes.UPDATE, new UpdateView());
        messageReaction.put(ClientCodes.INIT_INFO, new InfoView());
    }

    public IMessageCodeView get(final ClientCodes clientCodes) {
        return messageReaction.get(clientCodes);
    }
}
