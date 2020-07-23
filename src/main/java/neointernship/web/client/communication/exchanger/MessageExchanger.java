package neointernship.web.client.communication.exchanger;

import neointernship.web.client.communication.message.IMessage;

import java.util.concurrent.Exchanger;

public class MessageExchanger {
    private static final Exchanger<IMessage> exchanger = new Exchanger<>();

    private MessageExchanger(){}

    public static IMessage exchange(final IMessage message) throws InterruptedException {
        return exchanger.exchange(message);
    }
}
