package neointernship.client.communication.message.reaction.view;

import neointernship.client.player.IPlayer;

public interface IMessageCode {
    void execute(final IPlayer player) throws InterruptedException;
}
