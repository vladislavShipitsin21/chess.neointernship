package neointernship.web.client.communication.message.reaction.model;

import neointernship.web.client.player.IPlayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

public class EndGameModel implements IMessageCodeModel {
    private final Socket socket;

    public EndGameModel(final Socket socket){
        this.socket = socket;
    }

    @Override
    public void execute(final IPlayer player, final BufferedReader in, final BufferedWriter out) throws Exception {
        System.out.println("End game");
        if (in != null) in.close();
        if (out != null) out.close();
        if (socket != null) socket.close();
    }
}
