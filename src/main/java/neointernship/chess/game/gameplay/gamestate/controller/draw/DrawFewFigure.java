package neointernship.chess.game.gameplay.gamestate.controller.draw;

import neointernship.chess.game.model.mediator.IMediator;

public class DrawFewFigure implements IDrawController {

    @Override
    public boolean isDraw(IMediator mediator) {
        // найти полный список ничейных ситуаций
        return false;
    }
}
