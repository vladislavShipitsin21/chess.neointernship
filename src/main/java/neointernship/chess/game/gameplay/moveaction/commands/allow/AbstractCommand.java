package neointernship.chess.game.gameplay.moveaction.commands.allow;


import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;

public class AbstractCommand{

    protected final IMediator mediator;
    protected final IBoard board;

    public AbstractCommand(IBoard board,IMediator mediator) {
        this.mediator = mediator;
        this.board = board;
    }

}
