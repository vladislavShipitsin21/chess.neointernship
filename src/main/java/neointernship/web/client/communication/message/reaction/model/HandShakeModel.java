package neointernship.web.client.communication.message.reaction.model;

import neointernship.web.client.communication.message.ClientCodes;
import neointernship.web.client.communication.message.Message;
import neointernship.web.client.communication.serializer.MessageSerializer;
import neointernship.web.client.player.APlayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Scanner;

public class HandShakeModel implements IMessageCodeModel {
    @Override
    public void execute(final APlayer player, final BufferedReader in, final BufferedWriter out) throws Exception {
        final Scanner scanner = new Scanner(System.in);
        String answer = "";
        final Message message;

        for (int i = 0; i < 3 && !answer.equals("да") && !answer.equals("нет"); i++) {
            System.out.println("Оппонент найден.\nВы готовы? (да/нет):");
            answer = scanner.nextLine().trim().toLowerCase();
        }

        if (answer.equals("да")) {
            message = new Message(ClientCodes.YES);
        }else {
            message = new Message(ClientCodes.NO);
        }

        out.write(MessageSerializer.serialize(message) + "\n");
        out.flush();
    }
}
