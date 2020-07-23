package neointernship.web.client.view;

import neointernship.web.client.communication.exchanger.MessageExchanger;
import neointernship.web.client.communication.message.IMessage;
import neointernship.web.client.communication.message.ViewMessageReaction;
import neointernship.web.client.player.IPlayer;

public class View implements Runnable {
    private ViewMessageReaction viewMessageReaction;

    @Override
    public void run() {
        viewMessageReaction = new ViewMessageReaction();
        IPlayer player = null;

        while (true){
            try {
                final IMessage message = MessageExchanger.exchange(null);
                viewMessageReaction.get(message.getClientCodes()).execute(player);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
