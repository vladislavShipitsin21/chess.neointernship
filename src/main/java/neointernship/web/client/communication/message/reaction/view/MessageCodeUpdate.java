package neointernship.web.client.communication.message.reaction.view;

import neointernship.web.client.communication.data.update.IUpdate;
import neointernship.web.client.communication.exchanger.ExchangerForMessage;
import neointernship.web.client.communication.exchanger.ExchangerForUpdate;
import neointernship.web.client.communication.message.IMessage;
import neointernship.web.client.communication.message.Message;
import neointernship.web.client.communication.message.ClientCodes;
import neointernship.web.client.player.IPlayer;

public class MessageCodeUpdate implements IMessageCode {
    @Override
    public void execute(final IPlayer player) throws InterruptedException {
        final IUpdate update = ExchangerForUpdate.exchange(null);
        player.setMediator(update.getMediator());
        final IMessage mes = new Message(ClientCodes.OK);
        ExchangerForMessage.exchange(mes);
    }
}
