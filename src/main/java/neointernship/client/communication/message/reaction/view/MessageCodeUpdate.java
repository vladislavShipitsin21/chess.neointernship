package neointernship.client.communication.message.reaction.view;

import neointernship.client.communication.exchanger.ExchangerForMessage;
import neointernship.client.communication.message.IMessage;
import neointernship.client.communication.message.Message;
import neointernship.client.communication.message.MessageCode;
import neointernship.client.player.IPlayer;

public class MessageCodeUpdate implements IMessageCode {
    @Override
    public void execute(IPlayer player) throws InterruptedException {
        //IMediator mediator = .exchange(null);
        //player.setMediator(mediator);
        IMessage mes = new Message(MessageCode.OK);
        ExchangerForMessage.exchange(mes);
    }
}
