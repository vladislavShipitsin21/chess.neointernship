package neointernship.web.client.communication.message.reaction.model;

import neointernship.web.client.communication.data.update.Update;
import neointernship.web.client.communication.data.update.UpdateDto;
import neointernship.web.client.communication.exchanger.MessageExchanger;
import neointernship.web.client.communication.exchanger.UpdateExchanger;
import neointernship.web.client.communication.message.IMessage;
import neointernship.web.client.communication.serializer.MessageSerializer;
import neointernship.web.client.communication.serializer.UpdateSerializer;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class UpdateModel implements IMessageCodeModel {
    @Override
    public void execute(final IMessage message, final BufferedReader in, final BufferedWriter out) throws Exception {
        MessageExchanger.exchange(message);
        final String updateString = in.readLine();
        final UpdateDto updateDto = UpdateSerializer.deserialize(updateString);
        updateDto.validate();
        final Update update = new Update(updateDto.getMediator());
        UpdateExchanger.exchange(update);
    }
}
