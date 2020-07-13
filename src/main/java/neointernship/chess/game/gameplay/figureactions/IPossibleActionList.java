package neointernship.chess.game.gameplay.figureactions;

import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.Collection;
import java.util.Map;

public interface IPossibleActionList {

    void addNewFigure(final  Figure figure);
    void removeFigure(final  Figure figure);
    void clearList(final Figure figure);

    Collection<IField> getList(final Figure figure);
    void updateLists();

    Map<Figure,Collection<IField>> getMap();
}

