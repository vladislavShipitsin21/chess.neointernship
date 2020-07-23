package neointernship.chess.game.gameplay.gamestate.controller.draw;

import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.story.IStoryGame;

import java.util.Collection;
import java.util.HashSet;

public class DrawController {

    private final IMediator mediator;

    private final Collection<IDrawController> drawControllers;

    public DrawController(final IMediator mediator,
                          final IStoryGame storyGame) {
        this.mediator = mediator;

        this.drawControllers = new HashSet<>();
        drawControllers.add(new DrawOnlyKing());
        drawControllers.add(new DrawFewFigure());
        drawControllers.add(new DrawRepetitionPosition());
        drawControllers.add(new DrawFiftyStep(storyGame));
    }

    public boolean isDraw() {
        for(final IDrawController drawController : drawControllers){
            if(drawController.isDraw(mediator)){
                // todo возвращать что за ничья !
                return true;
            }
        }
        return false;
    }
}
