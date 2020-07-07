package neointernship.chess.game.model.playmap.board;

import neointernship.chess.game.model.figure.Figure;
import neointernship.chess.game.model.playmap.field.IField;

import java.lang.reflect.Field;
import java.util.ArrayList;

public interface IBoard {
    void initializeBoard();
    IField getField(final int x, final int y);
    ArrayList<Figure> getFigures();
}
