package neointernship.web.client.communication.message.reaction.model;

import neointernship.web.client.player.IPlayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public interface IMessageCodeModel {
    void execute(final IPlayer player, final BufferedReader in, final BufferedWriter out) throws Exception;
}
