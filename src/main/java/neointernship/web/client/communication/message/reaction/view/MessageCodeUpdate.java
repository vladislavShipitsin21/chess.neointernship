package neointernship.web.client.communication.message.reaction.view;

import neointernship.web.client.communication.exchanger.ExchangerForMessage;
import neointernship.web.client.communication.message.IMessage;
import neointernship.web.client.communication.message.Message;
import neointernship.web.client.communication.message.MessageCode;
import neointernship.web.client.player.IPlayer;

public class MessageCodeUpdate implements IMessageCode {
    @Override
    public void execute(IPlayer player) throws InterruptedException {
        //IMediator mediator = .exchange(null);
        //player.setMediator(mediator);
        IMessage mes = new Message(MessageCode.OK);
        ExchangerForMessage.exchange(mes);
    }
}
