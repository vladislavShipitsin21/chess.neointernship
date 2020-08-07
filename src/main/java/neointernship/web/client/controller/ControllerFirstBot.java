package neointernship.web.client.controller;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.logger.ErrorLoggerClient;
import neointernship.web.client.GUI.Input.InputVoid;
import neointernship.web.client.communication.message.ClientCodes;
import neointernship.web.client.communication.message.MessageDto;
import neointernship.web.client.communication.message.ModelMessageReaction;
import neointernship.web.client.communication.serializer.MessageSerializer;
import neointernship.web.client.player.APlayer;
import neointernship.web.client.player.FirstBot;

import java.io.*;
import java.net.Socket;

public class ControllerFirstBot implements Runnable {
    private BufferedReader in = null;
    private BufferedWriter out = null;
    private Socket socket;
    private Connection connection;
    private ModelMessageReaction modelMessageReaction;
    private APlayer player;
    private boolean endGame = false;

    private String name;
    private int i = 0;

    public ControllerFirstBot(int i) {
        name = "first bot";
//        name = "bot № " + i;
        this.i = i;
    }

    @Override
    public void run() {
        modelMessageReaction = new ModelMessageReaction(socket);

        startConnection();

        initPlayer();

        while (!endGame) {
            try {
                final String jsonMessage = in.readLine();
                final MessageDto messageDto = MessageSerializer.deserialize(jsonMessage);
                messageDto.validate();
                modelMessageReaction.get(messageDto.getClientCodes()).execute(player, in, out);
                if (messageDto.getClientCodes() == ClientCodes.END_GAME) endGame = true;
            } catch (final Exception e) {
                ErrorLoggerClient.getLogger(player.getName()).logException(e);
            }
        }
    }

    private void initPlayer() {
        player = new FirstBot(Color.BOTH, name, new InputVoid());
        ErrorLoggerClient.addLogger(name);
    }

    private void startConnection() {
        connection = new Connection();
        connection.connection();

        socket = connection.getSocket();

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (final IOException e) {
            ErrorLoggerClient.getLogger(player.getName()).logException(e);
        }
    }
}
