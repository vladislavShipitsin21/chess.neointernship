package neointernship.chess.client.controller;

import neointernship.chess.client.message.Message;
import neointernship.chess.client.message.MessageDto;
import neointernship.chess.client.message.MessageReaction;
import neointernship.chess.client.serializer.SerializerForMessage;
import java.io.*;
import java.net.Socket;

public class Controller implements Runnable{
    private BufferedReader in = null;
    private BufferedWriter out = null;
    private Socket socket;
    private Connection connection;
    private final MessageReaction messageReaction = new MessageReaction();

    @Override
    public void run() {
        //----------------------------------НУЖЕН КОННЕКТ СО СТОРОНЫ СЕРВЕРА-------------------------------------------
        /*startConnection();

        while (true) {
            try {
                final String jsonMessage = in.readLine();
                final MessageDto messageDto = SerializerForMessage.deserializer(jsonMessage);
                messageDto.validateMessageCode();
                final Message message = new Message(messageDto.getMessageCode(), messageDto.getData());
                messageReaction.get(message.getMessageCode()).execute(message, out);
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }*/
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
