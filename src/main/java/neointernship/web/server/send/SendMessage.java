package neointernship.web.server.send;

import java.io.BufferedWriter;
import java.io.IOException;

public class SendMessage {

    public static void send(final BufferedWriter out, final String msg) throws IOException {
        out.write(msg + "\n");
        out.flush();
    }
}
