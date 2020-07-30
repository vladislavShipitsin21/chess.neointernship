package neointernship.web.client.player;

import neointernship.chess.game.console.ConsoleBoardWriter;
import neointernship.chess.game.model.answer.AnswerSimbol;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.web.client.communication.message.TurnStatus;

import java.util.Scanner;

public class Player extends APlayer {
    private ConsoleBoardWriter consoleBoardWriter;

    public Player(final Color color, final String name) {
        super(color, name);
    }

    @Override
    public void init(final IMediator mediator, final IBoard board, final Color color) {
        super.init(mediator, board, color);

        this.consoleBoardWriter = new ConsoleBoardWriter(mediator, board);
//        consoleBoardWriter.printBoard();
    }

    @Override
    public IAnswer getAnswer() {
        final Scanner scanner = new Scanner(System.in);
        System.out.format("%s player turn to move: ", getName());
        final String input = scanner.nextLine();
        /*final String[] answerString = input.split(" ");

        final IAnswer answer = new Answer(Integer.parseInt(answerString[0]), Integer.parseInt(answerString[1]),
                Integer.parseInt(answerString[2]), Integer.parseInt(answerString[3]), 'Q');
        return answer;*/
        String[] strArr = input.split("-");

        return new AnswerSimbol(strArr[0].charAt(0), strArr[0].charAt(1), strArr[1].charAt(0), strArr[1].charAt(1));
    }

    @Override
    public void updateMediator(final IAnswer answer, final TurnStatus turnStatus) {
        super.updateMediator(answer, turnStatus);

//        consoleBoardWriter.printBoard();
    }
}
