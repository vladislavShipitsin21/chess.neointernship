package neointernship.chess.web.client.message.reaction;

import neointernship.chess.web.client.message.Message;

import java.io.BufferedWriter;
import java.io.IOException;

public interface IMessageCode {
    void execute(final Message message, final BufferedWriter out) throws InterruptedException, IOException;
}
