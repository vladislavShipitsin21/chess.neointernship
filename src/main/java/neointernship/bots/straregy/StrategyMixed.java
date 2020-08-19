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

public class StrategyMixed extends Strategy {

    private int countAnswer;
    private final StrategyRandom strategyRandom;

    public StrategyMixed(final Color color) {
        super(color);
        this.countAnswer = 0;
        strategyRandom = new StrategyRandom(color);
    }

    // todo дебют / миттельшпить / эндшпиль

    @Override
    public IAnswer getAnswer(final Position startPosition) {
        countAnswer++;

        /*if(countAnswer < 10){
            return getRandomAnswer(startPosition);
        }*/
        return null;
    }

    private IAnswer getRandomAnswer(final Position startPosition) {
        return strategyRandom.getAnswer(startPosition);
    }
}
