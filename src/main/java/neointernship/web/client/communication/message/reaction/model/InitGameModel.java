package neointernship.web.client.communication.message.reaction.model;

import neointernship.web.client.communication.data.initgame.IInitGame;
import neointernship.web.client.communication.data.initgame.InitGame;
import neointernship.web.client.communication.data.initgame.InitGameDto;
import neointernship.web.client.communication.exchanger.InitGameExchanger;
import neointernship.web.client.communication.exchanger.MessageExchanger;
import neointernship.web.client.communication.message.IMessage;
import neointernship.web.client.communication.serializer.InitGameSerializer;
import neointernship.web.client.communication.serializer.MessageSerializer;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class InitGameModel implements IMessageCodeModel {
    @Override
    public void execute(final IMessage message, final BufferedReader in, final BufferedWriter out) throws Exception {
        final String initGameString = in.readLine();
        final InitGameDto initGameDto = InitGameSerializer.deserialize(initGameString);
        initGameDto.validate();
        final IInitGame initGame = new InitGame(initGameDto.getMediator(), initGameDto.getBoard(), initGameDto.getColor());

        MessageExchanger.exchange(message);
        InitGameExchanger.exchange(initGame);

        final IMessage mes = MessageExchanger.exchange(null);
        out.write(MessageSerializer.serialize(mes) + "\n");
        out.flush();
    }
}
