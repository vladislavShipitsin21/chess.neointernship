package neointernship.chess.client.communication.message.reaction.view;

import neointernship.chess.client.player.IPlayer;

public interface IMessageCode {
    void execute(final IPlayer player) throws InterruptedException;
}
