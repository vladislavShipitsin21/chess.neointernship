package neointernship.web.client.communication.message.reaction.view;

import neointernship.web.client.player.IPlayer;

public interface IMessageCodeView {
    void execute(final IPlayer player) throws InterruptedException;
}
