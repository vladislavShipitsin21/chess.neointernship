package neointernship.bots.straregy;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.answer.AnswerSimbol;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.tree.BuilderTree;
import neointernship.tree.HelperBuilderTree;
import neointernship.tree.INode;

import java.util.List;
import java.util.Random;

public class Strategy {
    private final Color color;
    private int countAnswer;

    public Strategy(final Color color) {
        this.color = color;
        this.countAnswer = 0;
    }
    // todo дебют / миттельшпить / эндшпиль

    public IAnswer getAnswer(final Position startPosition){
        countAnswer++;

        /*if(countAnswer < 10){
            return getRandomAnswer(startPosition);
        }*/

        BuilderTree builderTree = new BuilderTree(4,color);
        INode root = builderTree.getTree(startPosition);

        return HelperBuilderTree.getAnswer(root);
    }

    private IAnswer getRandomAnswer(final Position startPosition){
        Random random = new Random();

        final IMediator mediator = startPosition.getMediator();
        final IPossibleActionList possibleActionList = startPosition.getPossibleActionList();
        final List<Figure> figures = (List<Figure>) mediator.getFigures(color);

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
