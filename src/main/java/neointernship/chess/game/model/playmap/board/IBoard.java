package neointernship.chess.game.model.playmap.board;

import neointernship.chess.game.model.playmap.field.IField;

import java.lang.reflect.Field;
import java.util.ArrayList;

public interface IBoard {
    IField getField(final int x, final int y);
    short getSize();
}