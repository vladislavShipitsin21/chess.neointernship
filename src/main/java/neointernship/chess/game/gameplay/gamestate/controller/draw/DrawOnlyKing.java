package neointernship.chess.game.gameplay.gamestate.controller.draw;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.story.IStoryGame;

public class DrawOnlyKing implements IDrawController {
    @Override
    public boolean isDraw(IMediator mediator) {
        return mediator.getFigures().size() == 2;
    }
}
