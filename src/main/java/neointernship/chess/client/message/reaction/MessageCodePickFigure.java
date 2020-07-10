package neointernship.chess.client.message.reaction;

import com.fasterxml.jackson.core.JsonProcessingException;
import neointernship.chess.client.message.Message;

import java.io.BufferedWriter;
import java.io.IOException;

import static neointernship.chess.client.Client.exchanger;
import static neointernship.chess.client.Client.mapper;

public class MessageCodePickFigure implements IMessageCode {
    @Override
    public void execute(final Message message, final BufferedWriter out) throws InterruptedException, IOException {
        final Message message1;
        exchanger.exchange(null);
        message1 = exchanger.exchange(null);
        final String jsonMessage = mapper.writeValueAsString(message1);
        out.write(String.valueOf(message1));
        out.flush();
    }
}
