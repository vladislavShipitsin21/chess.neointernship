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

    Figure getKing(final Color color);

    void addNewConnection(final IField field, final Figure figure);
    void deleteConnection(final IField field);
    void updateConnection(final IField field, final Figure figure);

    void clear();


}
