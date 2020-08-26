package neointernship.web.client.controller;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.logger.ErrorLoggerClient;
import neointernship.web.client.GUI.Input.Input;
import neointernship.web.client.communication.message.ClientCodes;
import neointernship.web.client.communication.message.MessageDto;
import neointernship.web.client.communication.message.ModelMessageReaction;
import neointernship.web.client.communication.serializer.MessageSerializer;
import neointernship.web.client.player.*;

import java.io.*;
import java.net.Socket;

public class Controller {
    private Input input;
    private BufferedReader in = null;
    private BufferedWriter out = null;
    private Socket socket;
    private Connection connection;
    private ModelMessageReaction modelMessageReaction;
    private APlayer player;
    private boolean endGame = false;

    public void start() throws InterruptedException {
        input = new Input();
        modelMessageReaction = new ModelMessageReaction(socket);

        startConnection();
        initPlayer();

        while (!endGame) {
            try {
                final String jsonMessage = in.readLine();
                final MessageDto messageDto = MessageSerializer.deserialize(jsonMessage);
                messageDto.validate();
                modelMessageReaction.get(messageDto.getClientCodes()).execute(player, in, out);

                if (messageDto.getClientCodes() == ClientCodes.END_GAME) {
                    endGame = true;
                }
            } catch (final Exception e) {
                ErrorLoggerClient.getLogger(player.getName()).logException(e);
            }
        }
    }

    private void initPlayer() throws InterruptedException {
        final PlayerType playerType = input.getPlayerType();
        String name = null;
        switch (playerType){
            case MINI_MAX:{
                player = new MiniMaxBot(Color.BOTH, 2);
                break;
            }
            case RANDOM:{
                player = new RandomBot(Color.BOTH);
                break;
            }
            case HUMAN:{
                name = input.getUserName().trim();
                final Color color = input.getColor();
                player = new Player(color, name, input);
                break;
            }
            case MIXID:{
                player = new MixidBot(Color.WHITE,2);
            }
        }
        name = player.getName();
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
