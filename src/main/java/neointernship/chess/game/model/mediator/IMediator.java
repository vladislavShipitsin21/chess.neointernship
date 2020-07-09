package neointernship.chess.game.model.mediator;

import neointernship.chess.game.model.figure.Figure;
import neointernship.chess.game.model.playmap.field.IField;

public interface IMediator {
    IField getField(final Figure figure);
    Figure getFigure(final IField field);
    void addNewConnection(final IField field, final Figure figure);
}