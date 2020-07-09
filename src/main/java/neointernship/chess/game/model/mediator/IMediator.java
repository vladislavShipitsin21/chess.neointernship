package neointernship.chess.game.model.mediator;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.Collection;

public interface IMediator {
    IField getField(final Figure figure);
    Figure getFigure(final IField field);

    Collection<Figure> getFigures(final Color color);
    Collection<Figure> getFigures();

    void addNewConnection(final IField field, final Figure figure);
}
