package neointernship.chess.game.model.figure.actions;

import neointernship.chess.game.model.figure.Figure;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.Collection;

public class PossibleActionList implements IPossibleActionList {

    @Override
    public void updateLists(Figure figure, IBoard board) {

    }

    @Override
    public Collection<IField> getMoveList(Figure figure) {
        return null;
    }

    @Override
    public Collection<IField> getAttackList(Figure figure) {
        return null;
    }
}
