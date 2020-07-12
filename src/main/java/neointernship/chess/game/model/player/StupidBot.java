package neointernship.chess.game.model.player;

import neointernship.chess.game.model.enums.Color;


public class StupidBot extends Player {

    public StupidBot(final Color color) {
        super( "stupid bot",color);
    }
    /*
    @Override
    public IAnswer getAnswer(IBoard board, IMediator mediator, IPossibleActionList list) {
        for (Figure figure : mediator.getFigures(getColor())){
            for (IField field : list.getList(figure)){
                return new Answer(mediator.getField(figure), field);
            }
        }
        return null;
    }
    */
}
