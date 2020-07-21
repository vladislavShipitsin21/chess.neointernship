package neointernship.chess.client.communication.message.reaction.view;

import neointernship.chess.client.communication.exchanger.ExchangerForMessage;
import neointernship.chess.client.communication.message.IMessage;
import neointernship.chess.client.communication.message.Message;
import neointernship.chess.client.communication.message.MessageCode;
import neointernship.chess.client.player.IPlayer;

public class MessageCodeUpdate implements IMessageCode {
    @Override
    public void execute(IPlayer player) throws InterruptedException {
        //IMediator mediator = .exchange(null);
        //player.setMediator(mediator);
        IMessage mes = new Message(MessageCode.OK);
        ExchangerForMessage.exchange(mes);
    }
}
