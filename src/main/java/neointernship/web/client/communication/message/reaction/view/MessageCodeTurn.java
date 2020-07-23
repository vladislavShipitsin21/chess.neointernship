package neointernship.web.client.communication.message.reaction.view;

import neointernship.web.client.communication.data.turn.Turn;
import neointernship.web.client.communication.exchanger.ExchangerForMessage;
import neointernship.web.client.communication.exchanger.ExchangerForTurn;
import neointernship.web.client.communication.message.IMessage;
import neointernship.web.client.communication.message.Message;
import neointernship.web.client.communication.message.MessageCode;
import neointernship.web.client.player.IPlayer;

public class MessageCodeTurn implements IMessageCode {
    @Override
    public void execute(final IPlayer player) throws InterruptedException {
        final IMessage mes = new Message(MessageCode.OK);
        ExchangerForMessage.exchange(mes);
        final Turn turn = new Turn(player.getAnswer());
        ExchangerForTurn.exchange(turn);
    }
}
