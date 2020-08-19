package neointernship.chess.game.model.player;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.List;
import java.util.Random;

public class RandomBot extends Bot {

    final Random random;

    public RandomBot(final Color color) {
        super("Random",color);
        random = new Random();
    }

    @Override
    public IAnswer getAnswer(final IMediator mediator, final IPossibleActionList possibleActionList) {
        time.start();

        final List<Figure> figures = (List<Figure>) mediator.getFigures(getColor());
        List<IField> fields;
        Figure figure;
        int index;

        do {
            index = random.nextInt(figures.size());
            figure = figures.get(index);
            fields = (List<IField>) possibleActionList.getRealList(figure);
        } while (fields.isEmpty());

        index = random.nextInt(fields.size());
        final IField finalField = fields.get(index);

        final IField startField = mediator.getField(figure);

        final IAnswer answer = new Answer(startField.getXCoord(), startField.getYCoord(),
                finalField.getXCoord(), finalField.getYCoord(), 'Q');

        time.finish();
        System.out.println(getName() + " - " + time.getTime());

        return answer;
    }

    private void printAnswer(final IField start, final IField finish) {
        System.out.println("Color = " + super.getColor());
        System.out.println(start.showField() + " - " + finish.showField());
    }

}
