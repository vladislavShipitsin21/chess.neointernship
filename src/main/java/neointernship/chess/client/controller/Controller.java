package neointernship.chess.client.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import neointernship.chess.client.message.Message;
import neointernship.chess.client.message.MessageCode;
import neointernship.chess.client.message.MessageDto;
import neointernship.chess.client.message.reaction.*;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class Controller implements Runnable{
    private static final String IP = "127.0.0.1";//"localhost";
    private static final int PORT = 8081;

    private String ip;
    private int port;
    private Socket socket = null;
    private BufferedReader in = null;
    private BufferedWriter out = null;
    private ObjectMapper mapper;
    private HashMap<MessageCode, MessageReaction> messageReaction;

    private void initController(final String ip, final int port) {
        this.ip = ip;
        this.port = port;
        mapper = new ObjectMapper();
        messageReaction = new HashMap<>();
        initMessageReaction(messageReaction);
    }

    private void initMessageReaction(final HashMap<MessageCode, MessageReaction> messageReaction) {
        messageReaction.put(MessageCode.CONNECT, new MessageCodeConnect());
        messageReaction.put(MessageCode.DRAW, new MessageCodeDraw());
        messageReaction.put(MessageCode.ERROR, new MessageCodeError());
        messageReaction.put(MessageCode.FIGURES_LIST, new MessageCodeFiguresList());
        messageReaction.put(MessageCode.LOSE, new MessageCodeLose());
        messageReaction.put(MessageCode.MOVE_FIGURE, new MessageCodeMoveFigure());
        messageReaction.put(MessageCode.OK, new MessageCodeOk());
        messageReaction.put(MessageCode.PAINT, new MessageCodePaint());
        messageReaction.put(MessageCode.PICK_FIGURE, new MessageCodePickFigure());
        messageReaction.put(MessageCode.WIN, new MessageCodeWin());
    }

    private void startController() {
        initController(IP, PORT);

        try {
            socket = new Socket(ip, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println(String.format("Client started, ip: %s, port: %d", ip, port));
        } catch (final IOException e) {
            System.err.println("Socket failed");
        }
    }

    @Override
    public void run() {
        String jsonMessage;
        MessageDto messageDto;
        Message message;

        startController();

        while (true) {
            try {
                jsonMessage = in.readLine();
                messageDto = mapper.readValue(jsonMessage, MessageDto.class);
                messageDto.validateMessageCode();
                message = new Message(messageDto.getMessageCode(), messageDto.getMes(), messageDto.getFigureMap());
                messageReaction.get(message.getMessageCode()).Reaction(message);
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }
}
