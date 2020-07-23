package neointernship.web.client.communication.message.reaction.view;

import neointernship.web.client.communication.data.turn.Turn;
import neointernship.web.client.communication.exchanger.MessageExchanger;
import neointernship.web.client.communication.exchanger.TurnExchanger;
import neointernship.web.client.communication.message.IMessage;
import neointernship.web.client.communication.message.Message;
import neointernship.web.client.communication.message.ClientCodes;
import neointernship.web.client.player.IPlayer;

public class TurnView implements IMessageCodeView {
    @Override
    public void execute(final IPlayer player) throws InterruptedException {
        final IMessage mes = new Message(ClientCodes.OK);
        MessageExchanger.exchange(mes);
        final Turn turn = new Turn(player.getAnswer());
        TurnExchanger.exchange(turn);
    }
}
