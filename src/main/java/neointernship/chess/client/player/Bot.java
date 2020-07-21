package neointernship.chess.client.player;

import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.List;
import java.util.Random;

public class Bot implements IPlayer{
    private IMediator mediator;
    private IBoard board;
    private Color color;
    private String name;


    private Random random;
    public Bot(final IMediator mediator, final IBoard board, final Color color){
        this.mediator = mediator;
        this.board = board;
        this.color = color;
    }

    @Override
    public IAnswer getAnswer() {
        List<Figure> figures = (List<Figure>) mediator.getFigures(getColor());
        List<IField> fields = null;
        Figure figure = null;
        int index;

       /* do {
            index = random.nextInt(figures.size());
            figure = figures.get(index);
            fields = (List<IField>) possibleActionList.getRealList(figure);
        } while (fields.isEmpty());*/

        index = random.nextInt(fields.size());
        IField finalField = fields.get(index);

        IField startField = mediator.getField(figure);

        return new Answer(startField.getXCoord(), startField.getYCoord(),
                finalField.getXCoord(), finalField.getYCoord());
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
    public void setMediator(final IMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public IMediator getMediator() {
        return mediator;
    }
}
