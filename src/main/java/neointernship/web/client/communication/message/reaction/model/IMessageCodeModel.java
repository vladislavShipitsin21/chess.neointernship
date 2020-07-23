package neointernship.web.client.communication.message.reaction.model;

import neointernship.web.client.communication.message.IMessage;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public interface IMessageCodeModel {
    void execute(final IMessage message, final BufferedReader in, final BufferedWriter out) throws Exception;
}
