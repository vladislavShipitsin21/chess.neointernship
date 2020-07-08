package neointernship.chess.game.model.figure.actions;

import neointernship.chess.game.model.figure.Figure;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.Collection;

public interface IPossibleActionList {
    void updateLists(final Figure figure, final IBoard board);

    Collection<IField> getMoveList(final Figure figure);
    Collection<IField> getAttackList(final Figure figure);
}

