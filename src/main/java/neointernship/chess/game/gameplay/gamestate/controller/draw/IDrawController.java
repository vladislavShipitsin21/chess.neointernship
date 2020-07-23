package neointernship.chess.game.gameplay.gamestate.controller.draw;

import neointernship.chess.game.model.mediator.IMediator;

public interface IDrawController {
    boolean isDraw(IMediator mediator);
    String getMessage();
}
