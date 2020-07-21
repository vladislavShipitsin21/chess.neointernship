package neointernship.chess.client.view;

import neointernship.chess.client.communication.exchanger.ExchangerForMessage;
import neointernship.chess.client.communication.message.IMessage;
import neointernship.chess.client.communication.message.MessageReactionForView;
import neointernship.chess.client.player.IPlayer;

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
