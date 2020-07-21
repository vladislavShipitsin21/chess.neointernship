package neointernship.chess.client.controller;

import neointernship.chess.client.communication.message.Message;
import neointernship.chess.client.communication.message.MessageCode;
import neointernship.chess.client.communication.message.MessageDto;
import neointernship.chess.client.communication.message.MessageReactionForModel;
import neointernship.chess.client.communication.serializer.SerializerForMessage;

import java.io.*;
import java.net.Socket;

public class Controller implements Runnable{
    private BufferedReader in = null;
    private BufferedWriter out = null;
    private Socket socket;
    private Connection connection;
    private MessageReactionForModel messageReactionForModel;

    @Override
    public void run() {
        messageReactionForModel = new MessageReactionForModel();

        startConnection();

        while (true) {
            try {
                final String jsonMessage = in.readLine();
                final MessageDto messageDto = SerializerForMessage.deserializer(jsonMessage);
                messageDto.validate();
                final Message message = new Message(messageDto.getMessageCode());
                messageReactionForModel.get(message.getMessageCode()).execute(message, in, out);
            } catch (final java.lang.Exception e) {
                e.printStackTrace();
                final Message errMessage = new Message(MessageCode.ERROR_TURN);
                try {
                    //out.write(SerializerForMessage.serializer(errMessage) + "\n");
                    out.flush();
                } catch (final IOException ioException) {
                    ioException.printStackTrace();
                }
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
