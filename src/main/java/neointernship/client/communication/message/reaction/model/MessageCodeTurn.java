package neointernship.client.communication.message.reaction.model;

import neointernship.client.communication.exchanger.ExchangerForMessage;
import neointernship.client.communication.message.IMessage;
import neointernship.client.communication.serializer.SerializerForMessage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class MessageCodeTurn implements IMessageCode {
    @Override
    public void execute(final IMessage message, final BufferedReader in, final BufferedWriter out) throws InterruptedException, IOException {
        ExchangerForMessage.exchange(message);
        final IMessage mes = ExchangerForMessage.exchange(null);
        out.write(SerializerForMessage.serializer(mes) + "\n");
        out.flush();
    }
}
