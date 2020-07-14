package neointernship.chess.client.message.reaction;

import neointernship.chess.client.message.Message;
import neointernship.chess.client.message.MessageCode;
import neointernship.chess.client.message.data.Data;
import neointernship.chess.client.message.data.Tern;
import neointernship.chess.client.serializer.SerializerForMessage;

import java.io.BufferedWriter;
import java.io.IOException;

import static neointernship.chess.client.Client.exchanger;

public class MessageCodeTern implements IMessageCode {
    @Override
    public void execute(final Message message, final BufferedWriter out) throws InterruptedException, IOException {
        exchanger.exchange(null);

        final Message messageFromView = exchanger.exchange(null);
        final Data<Integer[]> data = messageFromView.getData();
        final Integer[] tern = data.getData();

        final Message mes = new Message(MessageCode.TERN, new Tern(tern[0], tern[1], tern[2], tern[3]));
        out.write(SerializerForMessage.serializer(mes));
        out.flush();
    }
}
