package neointernship.client.communication.message.reaction.view;

import neointernship.client.communication.data.turn.Turn;
import neointernship.client.communication.exchanger.ExchangerForMessage;
import neointernship.client.communication.message.IMessage;
import neointernship.client.communication.message.Message;
import neointernship.client.communication.message.MessageCode;
import neointernship.client.player.IPlayer;

public class MessageCodeTurn implements IMessageCode {
    @Override
    public void execute(final IPlayer player) throws InterruptedException {
        final Turn turn = new Turn(player.getAnswer());
        final IMessage mes = new Message(MessageCode.TURN);
        ExchangerForMessage.exchange(mes);
    }
}
