package neointernship.chess.game.model.player;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;

public abstract class Bot extends Player {

    public Bot(String name, Color color) {
        super(name + " bot", color);
    }

    @Override
    public IAnswer getAnswer(final IMediator mediator, IPossibleActionList possibleActionList) {
        return null;
    }
}
