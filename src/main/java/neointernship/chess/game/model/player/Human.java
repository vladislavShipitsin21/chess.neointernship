package neointernship.chess.game.model.player;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.model.answer.AnswerSimbol;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;

import java.util.Scanner;

public class Human extends Player {
    public Human(String name, Color color) {
        super(name, color);
    }
    @Override
    public IAnswer getAnswer(final IMediator mediator, IPossibleActionList possibleActionList) {
        Scanner scanner = new Scanner(System.in);
        System.out.format("%s player turn to move: ", getName());
        String input = scanner.nextLine();
        String[] strArr = input.split("-");

        return new AnswerSimbol(strArr[0].charAt(0), strArr[0].charAt(1), strArr[1].charAt(0), strArr[1].charAt(1));
    }
}
