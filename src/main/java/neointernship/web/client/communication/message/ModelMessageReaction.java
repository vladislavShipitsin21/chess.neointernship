package neointernship.web.client.communication.message;


import neointernship.web.client.communication.message.reaction.model.*;

import java.util.HashMap;

public class ModelMessageReaction {
    private final HashMap<ClientCodes, IMessageCodeModel> messageReaction;

    public ModelMessageReaction() {
        this.messageReaction = new HashMap<>();
        initMessageReaction(messageReaction);
    }

    private void initMessageReaction(final HashMap<ClientCodes, IMessageCodeModel> messageReaction) {
        messageReaction.put(ClientCodes.TURN, new TurnModel());
        messageReaction.put(ClientCodes.UPDATE, new UpdateModel());
        messageReaction.put(ClientCodes.INIT_INFO, new InfoModel());
        messageReaction.put(ClientCodes.INIT_GAME, new InitGameModel());
    }

    public IMessageCodeModel get(final ClientCodes clientCodes) {
        return messageReaction.get(clientCodes);
    }
}
