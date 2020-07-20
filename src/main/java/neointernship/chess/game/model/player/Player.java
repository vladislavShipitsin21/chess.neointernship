package neointernship.chess.game.model.player;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Player implements IPlayer {
    private final Color color;
    private String name;

    private final Socket socket;

    private final BufferedReader in; // поток чтения из сокета
    private final BufferedWriter out; // поток завписи в сокет

    public Player(final Color color, final Socket socket) throws IOException {
        this.color = color;

        this.socket = socket;

        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Override
    public IAnswer getAnswer(IBoard board, IMediator mediator, IPossibleActionList list) {
        Scanner scanner = new Scanner(System.in);
        System.out.format("%s player turn to move: ", getName());
        String input = scanner.nextLine();
        String[] strArr = input.split(" ");
        return new Answer(Integer.parseInt(strArr[0]),
                Integer.parseInt(strArr[1]),
                Integer.parseInt(strArr[2]),
                Integer.parseInt(strArr[3]));
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    @Override
    public BufferedReader getReader() {
        return in;
    }

    @Override
    public BufferedWriter getWriter() {
        return out;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Socket getSocket() {
        return socket;
    }


}
