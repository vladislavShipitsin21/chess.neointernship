package neointernship.bots.straregy;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.answer.AnswerSimbol;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.List;
import java.util.Random;

public class StrategyRandom extends Strategy {
    private final Random random;
    public StrategyRandom(final Color color) {
        super(color);
        random = new Random();
    }

    @Override
    public IAnswer getAnswer(final Position startPosition) {

        final IMediator mediator = startPosition.getMediator();
        final IPossibleActionList possibleActionList = startPosition.getPossibleActionList();
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
        return new AnswerSimbol(
                startField.getXCoord(),
                startField.getYCoord(),
                finalField.getXCoord(),
                finalField.getYCoord(),
                'Q');
    }
}
