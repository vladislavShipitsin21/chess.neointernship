package neointernship.web.client.controller;

import neointernship.chess.game.model.enums.Color;
import neointernship.web.client.communication.message.ClientCodes;
import neointernship.web.client.communication.message.MessageDto;
import neointernship.web.client.communication.message.ModelMessageReaction;
import neointernship.web.client.communication.serializer.MessageSerializer;
import neointernship.web.client.player.Bot;
import neointernship.web.client.player.IPlayer;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Controller implements Runnable{
    private BufferedReader in = null;
    private BufferedWriter out = null;
    private Socket socket;
    private Connection connection;
    private ModelMessageReaction modelMessageReaction;
    private IPlayer player = new Bot(Color.WHITE, "Bot");
    private boolean endGame = false;

    @Override
    public void run() {
        modelMessageReaction = new ModelMessageReaction(socket);

        startConnection();

       // initPlayer();

        while (!endGame) {
            try {
                final String jsonMessage = in.readLine();
                final MessageDto messageDto = MessageSerializer.deserialize(jsonMessage);
                messageDto.validate();
                modelMessageReaction.get(messageDto.getClientCodes()).execute(player, in, out);
                if (messageDto.getClientCodes() == ClientCodes.END_GAME) endGame = true;
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initPlayer() {
        final Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Человек или бот?");
            final String playerType = scanner.nextLine().trim().toLowerCase();
        } while (!player.equals("человек") || !player.equals("бот"));


        do {
            System.out.println("Желаемый цвет: белые, черные или любой?");
            final String color = scanner.nextLine();
        } while (!player.equals("человек") || !player.equals("бот"));

        do {
            System.out.println("Желаемый цвет: белые, черные или любой?");
            final String color = scanner.nextLine();
        } while (!player.equals("человек") || !player.equals("бот"));


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
