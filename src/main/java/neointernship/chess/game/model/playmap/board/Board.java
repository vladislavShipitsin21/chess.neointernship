package neointernship.chess.game.model.playmap.board;

import neointernship.chess.game.model.playmap.field.IField;

import java.lang.reflect.Field;

public class Board implements IBoard {
    private static final short BOARD_SIZE = 8;
    private Field[][] fieldMatrix;

    @Override
    public IField getField(final int x, final int y) {
        return null;
    }

    @Override
    public short getSize() {
        return BOARD_SIZE;
    }
}
