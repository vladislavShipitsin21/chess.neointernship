package neointernship.chess.game.model.player;

import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;


public class StupidBot extends Player {

    public StupidBot(final Color color) {
        super( "stupid bot",color);
    }

    @Override
    public IAnswer makeTurn(IBoard board, IMediator mediator, IPossibleActionList list) {

        for(Figure figure : mediator.getFigures(getColor())){
            for(IField field : list.getList(figure)){
                return new Answer(mediator.getField(figure),field);
            }
        }
        return null;
    }
}
