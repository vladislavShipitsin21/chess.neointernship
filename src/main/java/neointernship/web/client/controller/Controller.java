package neointernship.web.client.controller;

import neointernship.web.client.communication.message.MessageDto;
import neointernship.web.client.communication.message.ModelMessageReaction;
import neointernship.web.client.communication.serializer.MessageSerializer;
import neointernship.web.client.player.Bot;
import neointernship.web.client.player.IPlayer;

import java.io.*;
import java.net.Socket;

public class Controller implements Runnable{
    private BufferedReader in = null;
    private BufferedWriter out = null;
    private Socket socket;
    private Connection connection;
    private ModelMessageReaction modelMessageReaction;
    private IPlayer player = new Bot();
    @Override
    public void run() {
        modelMessageReaction = new ModelMessageReaction();

        startConnection();

        while (true) {
            try {
                final String jsonMessage = in.readLine();
                final MessageDto messageDto = MessageSerializer.deserialize(jsonMessage);
                messageDto.validate();
                modelMessageReaction.get(messageDto.getClientCodes()).execute(player, in, out);
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void startConnection() {
        connection = new Connection();
        connection.connection();

        socket = connection.getSocket();

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
