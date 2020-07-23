package neointernship.web.client.communication.message.reaction.model;

import neointernship.web.client.communication.data.update.Update;
import neointernship.web.client.communication.data.update.UpdateDto;
import neointernship.web.client.communication.exchanger.ExchangerForMessage;
import neointernship.web.client.communication.exchanger.ExchangerForUpdate;
import neointernship.web.client.communication.message.IMessage;
import neointernship.web.client.communication.serializer.SerializerForMessage;
import neointernship.web.client.communication.serializer.SerializerForUpdate;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class MessageCodeUpdate implements IMessageCode {
    @Override
    public void execute(final IMessage message, final BufferedReader in, final BufferedWriter out) throws Exception {
        ExchangerForMessage.exchange(message);
        final String updateString = in.readLine();
        final UpdateDto updateDto = SerializerForUpdate.deserializer(updateString);
        updateDto.validate();
        final Update update = new Update(updateDto.getMediator());
        ExchangerForUpdate.exchange(update);
        final IMessage mes = ExchangerForMessage.exchange(null);
        out.write(SerializerForMessage.serializer(mes) + "\n");
        out.flush();
    }
}
