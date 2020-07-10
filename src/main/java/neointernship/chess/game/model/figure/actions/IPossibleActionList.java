package neointernship.chess.game.model.figure.actions;

import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.Collection;
import java.util.Map;

public interface IPossibleActionList {

    void addNewFigure(final  Figure figure);
    void removeFigure(final  Figure figure);
    void clearList(final Figure figure);

    Collection<IField> getList(final Figure figure);
    void updateLists(final Figure figure, final IBoard board, IMediator mediator);

    Map<Figure,Collection<IField>> getMap();
}

