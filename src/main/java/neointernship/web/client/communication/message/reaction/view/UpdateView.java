package neointernship.web.client.communication.message.reaction.view;

import neointernship.web.client.communication.data.update.IUpdate;
import neointernship.web.client.communication.exchanger.MessageExchanger;
import neointernship.web.client.communication.exchanger.UpdateExchanger;
import neointernship.web.client.communication.message.IMessage;
import neointernship.web.client.communication.message.Message;
import neointernship.web.client.communication.message.ClientCodes;
import neointernship.web.client.player.IPlayer;

public class UpdateView implements IMessageCodeView {
    @Override
    public void execute(final IPlayer player) throws InterruptedException {
        final IUpdate update = UpdateExchanger.exchange(null);
        player.setMediator(update.getMediator());
        final IMessage mes = new Message(ClientCodes.OK);
        MessageExchanger.exchange(mes);
    }
}
