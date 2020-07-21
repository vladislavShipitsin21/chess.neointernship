package neointernship.chess.client.communication.exchanger;

import neointernship.chess.client.communication.message.IMessage;

import java.util.concurrent.Exchanger;

public class ExchangerForMessage {
    private static final Exchanger<IMessage> exchanger = new Exchanger<>();

    private ExchangerForMessage(){}

    public static IMessage exchange(final IMessage message) throws InterruptedException {
        return exchanger.exchange(message);
    }
}
