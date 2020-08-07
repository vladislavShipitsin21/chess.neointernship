package neointernship.chess.game.model.player;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;

public class FirstBot extends Bot {

    public FirstBot(final Color color) {
        super("First", color);
    }

    @Override
    public IAnswer getAnswer(final IMediator mediator, IPossibleActionList possibleActionList) {
        return null;
    }
}
