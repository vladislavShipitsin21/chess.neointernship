package neointernship.web.client.controller;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.logger.ErrorLoggerClient;
import neointernship.web.client.communication.message.ClientCodes;
import neointernship.web.client.communication.message.MessageDto;
import neointernship.web.client.communication.message.ModelMessageReaction;
import neointernship.web.client.communication.serializer.MessageSerializer;
import neointernship.web.client.player.APlayer;
import neointernship.web.client.player.Bot;
import neointernship.web.client.player.Player;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class Controller {
    private BufferedReader in = null;
    private BufferedWriter out = null;
    private Socket socket;
    private Connection connection;
    private ModelMessageReaction modelMessageReaction;
    private APlayer player;
    private boolean endGame = false;

    public void start() {
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
        final Scanner scanner = new Scanner(System.in);
        String playerType;
        String colorType;

        final HashMap<String, Color> colors = new HashMap<>();
        final HashMap<String, APlayer> players = new HashMap<>();

        colors.put("белые", Color.WHITE);
        colors.put("черные", Color.BLACK);
        colors.put("любой", Color.BOTH);

        do {
            System.out.println("Желаемый цвет: белые, черные или любой?");
            colorType = scanner.nextLine().trim().toLowerCase();
        } while (!colors.containsKey(colorType));


        System.out.println("Введите имя:");
        final String name = scanner.nextLine().trim();

        players.put("человек", new Player(colors.get(colorType), name));
        players.put("бот", new Bot(colors.get(colorType), name));

        do {
            System.out.println("Человек или бот:");
            playerType = scanner.nextLine().trim().toLowerCase();
        } while (!players.containsKey(playerType));

        player = players.get(playerType);
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
