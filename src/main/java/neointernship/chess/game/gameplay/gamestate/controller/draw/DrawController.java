package neointernship.chess.game.gameplay.gamestate.controller.draw;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.logger.IGameLogger;

import java.util.Collection;
import java.util.HashSet;

public class DrawController {

    private final IMediator mediator;
    private final IPossibleActionList possibleActionList;
    private final IGameLogger gameLogger;
    private final IStoryGame storyGame;

    private final Collection<IDrawController> drawControllers;

    public DrawController(final IMediator mediator,
                          final IPossibleActionList possibleActionList,
                          final IGameLogger gameLogger,
                          final IStoryGame storyGame) {
        this.mediator = mediator;
        this.possibleActionList = possibleActionList;
        this.gameLogger = gameLogger;
        this.storyGame = storyGame;

        this.drawControllers = new HashSet<>();
        drawControllers.add(new DrawOnlyKing());
        drawControllers.add(new DrawFewFigure());
        drawControllers.add(new DrawRepetitionPosition());
        drawControllers.add(new DrawFiftyStep(storyGame));
    }

    public boolean isDraw() {
        for(final IDrawController drawController : drawControllers){
            if(drawController.isDraw(mediator)){
                gameLogger.logDraw(drawController);
                return true;
            }
        }
        return false;
    }
}
