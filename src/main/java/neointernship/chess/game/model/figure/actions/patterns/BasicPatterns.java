package neointernship.chess.game.model.figure.actions.patterns;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.ArrayList;

public final class BasicPatterns implements IBasicPatterns {

    private final int boardSize;
    private final IMediator mediator;
    private final IBoard board;

    public BasicPatterns(IMediator mediator, IBoard board) {
        this.mediator = mediator;
        this.board = board;
        boardSize = board.getSize();
    }

    public ArrayList<IField> getDiagonalFields(final Figure figure) {
        ArrayList<IField> possibleAttackFields = new ArrayList<>();
        IField currentField = mediator.getField(figure);
        boolean keepRightDownMove = true;
        boolean keepLeftUpMove = true;
        for (int i = 1; i < boardSize; i++) {
            if (keepRightDownMove && currentField.getXCoord() < boardSize && currentField.getYCoord() < boardSize) {
                keepRightDownMove = actionToAdd(figure.getColor(),currentField.getXCoord() + i, currentField.getYCoord() + i, possibleAttackFields);
            }

            if (keepLeftUpMove && currentField.getXCoord() > 0 && currentField.getYCoord() > 0) {
                keepLeftUpMove = actionToAdd(figure.getColor(),currentField.getXCoord() - i, currentField.getYCoord() - i, possibleAttackFields);
            }
        }

        boolean keepRightUpMove = true;
        boolean keepLeftDownMove = true;
        for (int i = 1; i < boardSize; i++) {
            if (keepRightUpMove && currentField.getXCoord() > 0 && currentField.getYCoord() < boardSize) {
                keepRightUpMove = actionToAdd(figure.getColor(),currentField.getXCoord() - i, currentField.getYCoord() + i, possibleAttackFields);
            }

            if (keepLeftDownMove && currentField.getXCoord() < boardSize && currentField.getYCoord() > 0) {
                keepLeftDownMove = actionToAdd(figure.getColor(),currentField.getXCoord() + i, currentField.getYCoord() - i, possibleAttackFields);
            }
        }

        return possibleAttackFields;
    }

    public ArrayList<IField> getHorizonVerticalFields(final Figure figure) {
        ArrayList<IField> possibleAttackFields = new ArrayList<>();

        IField currentField = mediator.getField(figure);

        boolean keepRightMove = true;
        boolean keepLeftMove = true;
        for (int i = 1; i < boardSize; i++) {
            if (keepRightMove) {
                keepRightMove = actionToAdd(figure.getColor(),currentField.getXCoord(), currentField.getYCoord() + i, possibleAttackFields);
            }
            if (keepLeftMove) {
                keepLeftMove = actionToAdd(figure.getColor(),currentField.getXCoord(), currentField.getYCoord() - i, possibleAttackFields);
            }
        }

        boolean keepUpMove = true;
        boolean keepDownMove = true;
        for (int i = 1; i < boardSize; i++) {
            if (keepUpMove) {
                keepUpMove = actionToAdd(figure.getColor(),currentField.getXCoord() - i, currentField.getYCoord(), possibleAttackFields);
            }
            if (keepDownMove) {
                keepDownMove = actionToAdd(figure.getColor(),currentField.getXCoord() + i, currentField.getYCoord(), possibleAttackFields);
            }
        }
        return possibleAttackFields;
    }

    public ArrayList<IField> getKnightFields(final Figure figure) {
        ArrayList<IField> possibleAttackFields = new ArrayList<>();

        IField currentField = mediator.getField(figure);

        int[] onesList = new int[] {1, -1};
        int[] twosList = new int[] {2, -2};

        for (int one : onesList) {
            for (int two : twosList) {
                actionToAdd(figure.getColor(),currentField.getXCoord() + one, currentField.getYCoord() + two, possibleAttackFields);
                actionToAdd(figure.getColor(),currentField.getXCoord() + two, currentField.getYCoord() + one, possibleAttackFields);
            }
        }

        return possibleAttackFields;
    }

    public ArrayList<IField> getPawnFields(final Figure figure) {
        ArrayList<IField> possibleAttackFields = new ArrayList<>();

        IField currentField = mediator.getField(figure);

        int colorMove = -1;
        if(figure.getColor() == Color.BLACK) colorMove = 1;

        int[] onesList = new int[] {1, -1};
        for (int one : onesList) {
            actionToAdd(figure.getColor(),
                    currentField.getXCoord() + colorMove,
                    currentField.getYCoord() + one, possibleAttackFields);
        }
        // todo пешка будет рубить прямо )
        return possibleAttackFields;
    }

    @Override
    public ArrayList<IField> getKingFields(Figure figure) {
        ArrayList<IField> possibleAttackFields = new ArrayList<>();

        IField currentField = mediator.getField(figure);

        int[] onesList = new int[] {1, -1};
        int[] twosList = new int[] {-1, 1};

        for (int one : onesList) {
            for (int two : twosList) {
                actionToAdd(figure.getColor(),currentField.getXCoord() + one, currentField.getYCoord() + two, possibleAttackFields);
                actionToAdd(figure.getColor(),currentField.getXCoord() + two, currentField.getYCoord() + one, possibleAttackFields);
            }
        }

        return possibleAttackFields;
    }

    /**
     * This method checks if it needed to add a field to the piece's attack list.
     * It checks if the field coordinates are within the dimensions of the Board.
     * If the fields contains some piece that has opposite color, the field added to the list.
     *
     * @param newFieldXCoord {@link Integer} row coordinate of the field being checked
     * @param newFieldYCoord {@link Integer} column coordinate of the field being checked
     * @param possibleMoveList a list where we add the field if it needed.
     * @return boolean value if moving through direction is possible (not covered with another piece).
     */
    private boolean actionToAdd(final Color colorFigura,final int newFieldXCoord, final int newFieldYCoord, ArrayList<IField> possibleMoveList) {
        if (!(newFieldXCoord >= 0 && newFieldXCoord < boardSize && newFieldYCoord >= 0 && newFieldYCoord < boardSize)) {
            return false;
        }
        IField newField = board.getField(newFieldXCoord, newFieldYCoord);
        if (mediator.getFigure(newField) != null) {
            if (mediator.getFigure(newField).getColor().equals(colorFigura)) {   // would be changed during model development.
                possibleMoveList.add(newField);
            }
            return false;
        }
        return true;
    }
}
