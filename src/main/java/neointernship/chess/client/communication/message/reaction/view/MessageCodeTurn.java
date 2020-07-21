package neointernship.chess.client.communication.message.reaction.view;

import neointernship.chess.client.communication.data.turn.Turn;
import neointernship.chess.client.communication.exchanger.ExchangerForMessage;
import neointernship.chess.client.communication.message.IMessage;
import neointernship.chess.client.communication.message.Message;
import neointernship.chess.client.communication.message.MessageCode;
import neointernship.chess.client.player.IPlayer;

public class MessageCodeTurn implements IMessageCode {
    @Override
    public void execute(final IPlayer player) throws InterruptedException {
        final Turn turn = new Turn(player.getAnswer());
        final IMessage mes = new Message(MessageCode.TURN);
        ExchangerForMessage.exchange(mes);
    }
}
