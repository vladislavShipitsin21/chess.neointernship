package neointernship.web.client.view;

import neointernship.web.client.communication.exchanger.ExchangerForMessage;
import neointernship.web.client.communication.message.IMessage;
import neointernship.web.client.communication.message.MessageReactionForView;
import neointernship.web.client.player.IPlayer;

public class View implements Runnable {
    private MessageReactionForView messageReactionForView;

    @Override
    public void run() {
        messageReactionForView = new MessageReactionForView();
        IPlayer player = null;

        while (true){
            try {
                final IMessage message = ExchangerForMessage.exchange(null);
                messageReactionForView.get(message.getMessageCode()).execute(player);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
