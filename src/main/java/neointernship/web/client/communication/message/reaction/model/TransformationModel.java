package neointernship.web.client.communication.message.reaction.model;

import neointernship.web.client.communication.data.transformation.Transformation;
import neointernship.web.client.communication.serializer.TransformationSerializer;
import neointernship.web.client.player.APlayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.*;

public class TransformationModel implements IMessageCodeModel {

    @Override
    public void execute(final APlayer player, final BufferedReader in, final BufferedWriter out) throws Exception {
        final Scanner scanner = new Scanner(System.in);

        final List<Character> figureList = Arrays.asList('Q', 'K', 'B', 'R');
        char figure;

        do {
            System.out.println("В какую фигуру обратить пешку:\n1.Q - ферзь\n2.B - слон\n3.N - конь\n4.R - ладья");
            final String string = scanner.nextLine().trim().toUpperCase();
            figure = string.charAt(0);
        } while (!figureList.contains(figure));

        final Transformation transformation = new Transformation(figure);
        out.write(TransformationSerializer.serialize(transformation) + "\n");
        out.flush();
    }
}
