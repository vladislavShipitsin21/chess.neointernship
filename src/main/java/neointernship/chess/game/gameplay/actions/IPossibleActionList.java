package neointernship.chess.game.gameplay.actions;

import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.Collection;
import java.util.Map;

public interface IPossibleActionList {
    Collection<IField> getList(final Figure figure);
    void updateLists();

}

