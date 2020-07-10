package neointernship.chess.client.controller;

import neointernship.chess.client.message.Message;
import neointernship.chess.client.message.MessageDto;
import neointernship.chess.client.message.MessageReaction;
import java.io.*;
import java.net.Socket;
import static neointernship.chess.client.Client.mapper;

public class Controller implements Runnable{
    private static final String IP = "127.0.0.1";//"localhost";
    private static final int PORT = 8081;

    private String ip;
    private int port;
    private Socket socket = null;
    private BufferedReader in = null;
    private BufferedWriter out = null;
    private MessageReaction messageReaction;

    @Override
    public void run() {
        String jsonMessage;
        MessageDto messageDto;
        Message message;

        startController(IP, PORT);

        while (true) {
            try {
                jsonMessage = in.readLine();
                messageDto = mapper.readValue(jsonMessage, MessageDto.class);
                messageDto.validateMessageCode();
                message = new Message(messageDto.getMessageCode(), messageDto.getMes(), messageDto.getFigureMap());
                messageReaction.get(message.getMessageCode()).execute(message, out);
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void startController(final String ip, final int port) {
        this.ip = ip;
        this.port = port;
        messageReaction = new MessageReaction();

        try {
            socket = new Socket(ip, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println(String.format("Client started, ip: %s, port: %d", ip, port));
        } catch (final IOException e) {
            System.err.println("Socket failed");
        }
    }
}
