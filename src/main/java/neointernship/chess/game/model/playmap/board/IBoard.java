package neointernship.chess.game.model.playmap.board;

import neointernship.chess.game.model.figure.Figure;
import neointernship.chess.game.model.playmap.field.IField;

public interface IBoard {
    void initializeBoard();
    IField getField(final int x, final int y);
    Figure getFigure(final IField field);
}
