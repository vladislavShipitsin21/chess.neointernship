package neointernship.web.client.player;

import neointernship.chess.game.console.ConsoleBoardWriter;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.game.story.StoryGame;
import neointernship.web.client.communication.message.ChessCodes;
import neointernship.web.client.communication.message.ChessCodesReaction;

import java.util.Scanner;

public class Player implements IPlayer {
    private IMediator mediator;
    private IBoard board;
    private Color color;
    private IStoryGame storyGame;
    private IPossibleActionList possibleActionList;
    private final String name;
    private ChessCodesReaction chessCodesReaction;
    private ConsoleBoardWriter consoleBoardWriter;

    public Player(final Color color, final String name) {
        this.color = color;
        this.name = name;
    }


    @Override
    public void init(final IMediator mediator, final IBoard board, final Color color) {
        this.mediator = mediator;
        this.board = board;
        this.color = color;
        this.storyGame = new StoryGame(mediator);
        this.possibleActionList = new PossibleActionList(board, mediator,storyGame);
        this.chessCodesReaction = new ChessCodesReaction(board, mediator);
        this.consoleBoardWriter = new ConsoleBoardWriter(mediator, board);
        consoleBoardWriter.printBoard();
    }

    @Override
    public IAnswer getAnswer() {
        final Scanner scanner = new Scanner(System.in);
        System.out.format("%s player turn to move: ", getName());
        final String input = scanner.nextLine();
        final String[] answerString = input.split(" ");

        final IAnswer answer = new Answer(Integer.parseInt(answerString[0]), Integer.parseInt(answerString[1]),
                Integer.parseInt(answerString[2]), Integer.parseInt(answerString[3]), 'Q');
        return answer;
        /*String[] strArr = input.split("-");

        return new AnswerSimbol( strArr[0].charAt(0), strArr[0].charAt(1), strArr[1].charAt(0), strArr[1].charAt(1));*/
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void updateMediator(final IAnswer answer, final ChessCodes chessCode) {
        final IField startField = board.getField(answer.getStartX(), answer.getStartY());
        final Figure startFigure = mediator.getFigure(startField);
        storyGame.update(startFigure);

        chessCodesReaction.get(chessCode).execute(answer);
        consoleBoardWriter.printBoard();
    }

    @Override
    public IMediator getMediator() {
        return mediator;
    }
}
