package neointernship.chess.game.model.playmap.board;

import neointernship.chess.game.model.figure.Figure;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.ArrayList;

public interface IBoard {
    void initializeBoard();
    IField getField(final int x, final int y);
    IField getField(final Figure figure);
    Figure getFigure(final IField field);
    ArrayList<Figure> getFigures();
}
