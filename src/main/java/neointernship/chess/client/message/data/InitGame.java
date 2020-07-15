package neointernship.chess.client.message.data;

import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;

public class InitGame implements Data{
    private Object[] objects;
    private IBoard board;
    private IMediator mediator;


    @Override
    public Object getData() {
        return null;
    }
}
