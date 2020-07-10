package neointernship.chess.game.model.player;

import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.List;
import java.util.Random;

public class RandomBot implements IPlayer {
    private final Color color;
    private final String name;

    Random random;

    public RandomBot() {
        color = Color.BLACK;
        name = "Random bot";
        random = new Random();
    }

    @Override
    public IAnswer getAnswer(IBoard board, IMediator mediator, IPossibleActionList list) {

        List<Figure> figures = (List<Figure>) mediator.getFigures(getColor());
        List<IField> fields;
        Figure figure;
        int index;

        do {
            index = random.nextInt(figures.size());
            figure = figures.get(index);
            fields = (List<IField>) list.getList(figure);
        }while (fields.isEmpty());

        index = random.nextInt(fields.size());
        IField field = fields.get(index);

        return new Answer(mediator.getField(figure),field);
    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
