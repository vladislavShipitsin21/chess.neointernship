package neointernship.web.client.communication.message.reaction.model;

import neointernship.web.client.communication.data.info.IInfo;
import neointernship.web.client.communication.exchanger.ExchangerForMessage;
import neointernship.web.client.communication.exchanger.ExchangerForInfo;
import neointernship.web.client.communication.message.IMessage;
import neointernship.web.client.communication.message.Message;
import neointernship.web.client.communication.message.ClientCodes;
import neointernship.web.client.communication.serializer.MessageSerializer;
import neointernship.web.client.communication.serializer.InfoSerializer;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class MessageCodeInfo implements IMessageCode {
    @Override
    public void execute(final IMessage message, final BufferedReader in, final BufferedWriter out) throws Exception {
        ExchangerForMessage.exchange(message);
        final IInfo name = ExchangerForInfo.exchange(null);

        final IMessage mes = new Message(ClientCodes.NAME);
        out.write(MessageSerializer.serialize(mes) + "\n");
        out.flush();

        out.write(InfoSerializer.serializer(name) + "\n");
        out.flush();
    }
}
