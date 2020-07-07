package neointernship.chess.game.model.player;

import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.Figure;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;


public class StupidBot extends Player {

    public StupidBot(final Color color) {
        super( "stupid bot",color);
    }

    @Override
    public IAnswer makeTurn(final IBoard board) {
        for(Figure figure : board.getFigures()){
            for(IField field : figure.getPossibleMoveList()){
                // todo нет ли сейчас шаха и не приведет ли меня этот ход к шаху???
                return (IAnswer) new Answer(board.getField(figure),field);
            }
        }
        return null;
    }

}
