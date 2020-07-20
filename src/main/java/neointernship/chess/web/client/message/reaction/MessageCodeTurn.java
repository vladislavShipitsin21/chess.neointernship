package neointernship.chess.web.client.message.reaction;

import neointernship.chess.web.client.message.Message;
import neointernship.chess.web.client.message.MessageCode;
import neointernship.chess.web.client.message.data.Data;
import neointernship.chess.web.client.message.data.Turn;
import neointernship.chess.web.client.serializer.SerializerForMessage;

import java.io.BufferedWriter;
import java.io.IOException;

import static neointernship.chess.web.client.Client.exchanger;

public class MessageCodeTurn implements IMessageCode {
    @Override
    public void execute(final Message message, final BufferedWriter out) throws InterruptedException, IOException {
        exchanger.exchange(null);

        final Message messageFromView = exchanger.exchange(null);
        final Data<Integer[]> data = messageFromView.getData();
        final Integer[] turn = data.getData();

        final Message mes = new Message(MessageCode.TURN, new Turn(turn[0], turn[1], turn[2], turn[3]));
        out.write(SerializerForMessage.serializer(mes));
        out.flush();
    }
}
