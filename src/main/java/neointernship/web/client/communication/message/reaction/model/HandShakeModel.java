package neointernship.web.client.communication.message.reaction.model;

import neointernship.web.client.communication.message.Message;
import neointernship.web.client.communication.serializer.MessageSerializer;
import neointernship.web.client.player.APlayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class HandShakeModel implements IMessageCodeModel {

    @Override
    public void execute(final APlayer player, final BufferedReader in, final BufferedWriter out) throws Exception {
        final Message message = new Message(player.getHandShakeAnswer());

        out.write(MessageSerializer.serialize(message) + "\n");
        out.flush();
    }
}
