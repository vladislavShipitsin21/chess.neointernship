package neointernship.web.client.communication.message.reaction.model;

import neointernship.web.client.communication.data.info.IInfo;
import neointernship.web.client.communication.exchanger.ExchangerForMessage;
import neointernship.web.client.communication.exchanger.ExchangerForInfo;
import neointernship.web.client.communication.message.IMessage;
import neointernship.web.client.communication.message.Message;
import neointernship.web.client.communication.message.MessageCode;
import neointernship.web.client.communication.serializer.SerializerForMessage;
import neointernship.web.client.communication.serializer.SerializerForInfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class MessageCodeInfo implements IMessageCode {
    @Override
    public void execute(final IMessage message, final BufferedReader in, final BufferedWriter out) throws Exception {
        ExchangerForMessage.exchange(message);
        final IInfo name = ExchangerForInfo.exchange(null);

        final IMessage mes = new Message(MessageCode.NAME);
        out.write(SerializerForMessage.serializer(mes) + "\n");
        out.flush();

        out.write(SerializerForInfo.serializer(name) + "\n");
        out.flush();
    }
}
