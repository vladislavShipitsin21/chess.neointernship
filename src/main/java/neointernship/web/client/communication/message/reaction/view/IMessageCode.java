package neointernship.web.client.communication.message.reaction.view;

import neointernship.web.client.player.IPlayer;

public interface IMessageCode {
    void execute(final IPlayer player) throws InterruptedException;
}
