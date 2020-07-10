package neointernship.chess.client.message.reaction;

import com.fasterxml.jackson.core.JsonProcessingException;
import neointernship.chess.client.message.Message;

import java.io.BufferedWriter;
import java.io.IOException;

public interface IMessageCode {
    void execute(Message message, BufferedWriter out) throws InterruptedException, IOException;
}
