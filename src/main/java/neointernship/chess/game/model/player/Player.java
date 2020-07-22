package neointernship.chess.game.model.player;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.AnswerSimbol;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Player implements IPlayer {
    private final Color color;
    private final String name;

    public Player(final String name, final Color color) {
        this.color = color;
        this.name = name;
    }

    /*@Override
    public IAnswer getAnswer(final IMediator mediator, IPossibleActionList list) {
        Scanner scanner = new Scanner(System.in);
        System.out.format("%s player turn to move: ", getName());
        String input = scanner.nextLine();
        String[] strArr = input.split(" ");
        return new Answer(Integer.parseInt(strArr[0]),
                Integer.parseInt(strArr[1]),
                Integer.parseInt(strArr[2]),
                Integer.parseInt(strArr[3]));
    }*/

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name + " " + color;
    }
}
