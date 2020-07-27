package neointernship.web.client.communication.message.reaction.model;

import neointernship.web.client.communication.data.turn.Turn;
import neointernship.web.client.communication.serializer.AnswerSerializer;
import neointernship.web.client.player.APlayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class TurnModel implements IMessageCodeModel {
    @Override
    public void execute(final APlayer player, final BufferedReader in, final BufferedWriter out) throws IOException {
        final Turn turn = new Turn(player.getAnswer());
        out.write(AnswerSerializer.serialize(turn) + "\n");
        out.flush();
    }
}
