package neointernship.chess.game.model.playmap.board;

import neointernship.chess.game.model.playmap.field.*;

public class Board implements IBoard {

    private static final short BOARD_SIZE = 8;
    private IField[][] fieldMatrix;

    public Board() {
        initializeBoard();
    }

    /**
     * инициализируем матрицу состоящую из клеток
     */
    private void initializeBoard() {
        fieldMatrix = new IField[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE ; j++) {
                fieldMatrix[i][j] = new Field(i,j);
            }
        }
    }

    @Override
    public IField getField(final int x, final int y) {
        return fieldMatrix[x][y];
    }

    @Override
    public short getSize() {
        return BOARD_SIZE;
    }
}
