package neointernship.web.client.communication.message.reaction.model;

import neointernship.web.client.communication.data.turn.ITurn;
import neointernship.web.client.communication.exchanger.MessageExchanger;
import neointernship.web.client.communication.exchanger.TurnExchanger;
import neointernship.web.client.communication.message.IMessage;
import neointernship.web.client.communication.message.Message;
import neointernship.web.client.communication.message.ClientCodes;
import neointernship.web.client.communication.serializer.MessageSerializer;
import neointernship.web.client.communication.serializer.AnswerSerializer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class TurnModel implements IMessageCodeModel {
    @Override
    public void execute(final IMessage message, final BufferedReader in, final BufferedWriter out) throws InterruptedException, IOException {
        MessageExchanger.exchange(message);
        final IMessage mes = new Message(ClientCodes.TURN);

        out.write(MessageSerializer.serialize(mes) + "\n");
        out.flush();

        final ITurn turn = TurnExchanger.exchange(null);
        out.write(AnswerSerializer.serialize(turn));
        out.flush();
    }
}
