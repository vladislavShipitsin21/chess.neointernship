package neointernship.chess.web.server.connection;

import neointernship.chess.game.model.player.IPlayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

public class UserConnection {
    private final BufferedReader in;
    private final BufferedWriter out;
    private final IPlayer player;
    private final Socket socket;

    public UserConnection(final BufferedReader in,
                            final BufferedWriter out,
                            final IPlayer player,
                          final Socket socket) {
        this.in = in;
        this.out = out;
        this.player = player;
        this.socket = socket;
    }

    public BufferedReader getIn() {
        return in;
    }

    public BufferedWriter getOut() {
        return out;
    }

    public IPlayer getPlayer() {
        return player;
    }

    public Socket getSocket() {
        return socket;
    }
}
