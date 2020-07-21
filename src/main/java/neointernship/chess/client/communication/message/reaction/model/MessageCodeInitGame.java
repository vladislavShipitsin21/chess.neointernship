package neointernship.chess.client.communication.message.reaction.model;

import neointernship.chess.client.communication.data.initgame.IInitGame;
import neointernship.chess.client.communication.data.initgame.InitGame;
import neointernship.chess.client.communication.exchanger.ExchangerForInitGame;
import neointernship.chess.client.communication.exchanger.ExchangerForMessage;
import neointernship.chess.client.communication.message.IMessage;
import neointernship.chess.client.communication.serializer.SerializerForInitGame;
import neointernship.chess.client.communication.serializer.SerializerForMessage;
import neointernship.chess.game.model.playmap.field.Field;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class MessageCodeInitGame implements IMessageCode {
    @Override
    public void execute(final IMessage message, final BufferedReader in, final BufferedWriter out) throws Exception {
        final String initGameString = in.readLine();
        final InitGame initGame = SerializerForInitGame.deserializer(initGameString);
        initGame.validate();
        final IInitGame initGames = new InitGame(initGame.getMediator(), initGame.getBoard(), initGame.getColor());
        System.out.println(initGames.getMediator().getFigures());
        System.out.println(initGames.getMediator().getFigure(new Field(1, 1)).getClass());
        ExchangerForMessage.exchange(message);
        ExchangerForInitGame.exchange(initGame);
        final IMessage mes = ExchangerForMessage.exchange(null);
        out.write(SerializerForMessage.serializer(mes) + "\n");
        out.flush();
    }
}
