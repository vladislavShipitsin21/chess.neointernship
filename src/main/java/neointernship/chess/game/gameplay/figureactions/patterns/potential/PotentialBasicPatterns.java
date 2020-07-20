package neointernship.chess.game.gameplay.figureactions.patterns.potential;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.Pawn;
import neointernship.chess.game.model.figure.piece.Rook;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;

import java.util.ArrayList;

public final class PotentialBasicPatterns implements IPotentialBasicPatterns {
    private final int boardSize;
    private final IMediator mediator;
    private final IBoard board;
    private final IStoryGame storyGame;

    public PotentialBasicPatterns(final IMediator mediator,final IBoard board,final IStoryGame storyGame) {
        this.mediator = mediator;
        this.board = board;
        boardSize = board.getSize();
        this.storyGame = storyGame;
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
                actionToAdd(figure.getColor(),
                        currentField.getXCoord() + one,
                        currentField.getYCoord() + two,
                        possibleAttackFields);
                actionToAdd(figure.getColor(),
                        currentField.getXCoord() + two,
                        currentField.getYCoord() + one,
                        possibleAttackFields);
            }
        }

        return possibleAttackFields;
    }

    public ArrayList<IField> getPawnFields(final Figure figure) {
        ArrayList<IField> possibleAttackFields = new ArrayList<>();
        IField currentField = mediator.getField(figure);

        int offset = (figure.getColor() == Color.BLACK) ? 1 : -1 ;

        int[] onesList = new int[] {1, -1};
        for (int one : onesList) {
            addAttackField(figure.getColor(), currentField.getXCoord() + offset, currentField.getYCoord() + one, possibleAttackFields);
        }
        addMoveField(currentField.getXCoord() + offset, currentField.getYCoord(), possibleAttackFields);

        if(
                currentField.getXCoord() == 3
                &&
                figure.getColor() == Color.WHITE
                &&
                storyGame.getLastFigureMove().getColor() == Color.BLACK
        ){
            Figure lastFigure = storyGame.getLastFigureMove();
            if(lastFigure.getClass() == Pawn.class){
                IField lastField = storyGame.getLastFieldFigure(lastFigure);
                if(lastField.getXCoord() == 1 && mediator.getField(lastFigure).getXCoord() == 3) {
                    if (Math.abs(lastField.getYCoord() - currentField.getYCoord()) == 1) {
                        possibleAttackFields.add(new Field(2,lastField.getYCoord()));
                    }
                }
            }
        }
        if(currentField.getXCoord() == 4 && figure.getColor() == Color.BLACK && storyGame.getLastFigureMove().getColor() == Color.WHITE){
            Figure lastFigure = storyGame.getLastFigureMove();
            if(lastFigure.getClass() == Pawn.class){
                IField lastField = storyGame.getLastFieldFigure(lastFigure);
                if(lastField.getXCoord() == 6 && mediator.getField(lastFigure).getXCoord() == 4) {
                    if (Math.abs(lastField.getYCoord() - currentField.getYCoord()) == 1) {
                        possibleAttackFields.add(new Field(5,lastField.getYCoord()));
                    }
                }
            }
        }

        if (currentField.getXCoord() == 6 && figure.getColor() == Color.WHITE) {
            offset = -  2;
            addMoveField(currentField.getXCoord() + offset, currentField.getYCoord(), possibleAttackFields);
        }
        if (currentField.getXCoord() == 1 && figure.getColor() == Color.BLACK) {
            offset = 2;
            addMoveField(currentField.getXCoord() + offset, currentField.getYCoord(), possibleAttackFields);
        }

        return possibleAttackFields;
    }

    @Override
    public ArrayList<IField> getKingFields(Figure king) {
        ArrayList<IField> possibleAttackFields = new ArrayList<>();

        IField fieldKing = mediator.getField(king);

        int[] onesList = new int[] {1, -1};

        for (int one : onesList) {
            for (int two : onesList) {

                actionToAdd(king.getColor(),
                        fieldKing.getXCoord() + one,
                        fieldKing.getYCoord() + two,
                        possibleAttackFields);

            }
        }

        for (int one : onesList) {
            actionToAdd(king.getColor(),
                    fieldKing.getXCoord() + one,
                    fieldKing.getYCoord(),
                    possibleAttackFields);

            actionToAdd(king.getColor(),
                    fieldKing.getXCoord(),
                    fieldKing.getYCoord() + one,
                    possibleAttackFields);
        }

        // если король не ходил
        if(!storyGame.isMove(king)){

            for (Figure rook : mediator.getFigures(king.getColor())){
                // если есть ладья которой не ходил
                if(rook.getClass() == Rook.class && !storyGame.isMove(rook)){
                    // если между ними нет других фигур
                    boolean haveFigure = false;
                    IField fieldRook = mediator.getField(rook);
                    int dif = fieldKing.getYCoord() < fieldRook.getYCoord() ? 1 : -1;

                    IField fieldTemp = board.getField(fieldKing.getXCoord(),fieldKing.getYCoord() + dif);

                    while (fieldTemp.getYCoord() != fieldRook.getYCoord()){
                        if(mediator.getFigure(fieldTemp) != null){
                            haveFigure = true;
                        }
                        fieldTemp = board.getField(fieldKing.getXCoord(),fieldTemp.getYCoord() + dif);
                    }
                    //если фигур нет
                    if(!haveFigure){
                        IField finalField = board.getField(fieldKing.getXCoord(),fieldKing.getYCoord() + (2 * dif));
                        possibleAttackFields.add(finalField);
                    }
                }
            }
        }
        return possibleAttackFields;
    }

    /**
     * This method checks if it needed to add a field to the piece's attack list.
     * It checks if the field coordinates are within the dimensions of the Board.
     * If the fields contains some piece that has opposite color, the field added to the list.
     *
     * @param newFieldYCoord {@link Integer} column coordinate of the field being checked
     * @param possibleMoveList a list where we add the field if it needed.
     * @return boolean value if moving through direction is possible (not covered with another piece).
     */
    private boolean actionToAdd(final Color color, final int newFieldXCoord, final int newFieldYCoord, ArrayList<IField> possibleMoveList) {
        if (!validCoordinates(newFieldXCoord, newFieldYCoord)) {
            return false;
        }
        IField field = board.getField(newFieldXCoord, newFieldYCoord);
        Figure figure = mediator.getFigure(field);

        addMoveField(newFieldXCoord, newFieldYCoord, possibleMoveList);
        addAttackField(color, newFieldXCoord, newFieldYCoord, possibleMoveList);

        return figure == null;
    }

    private void addMoveField(final int newFieldXCoord, final int newFieldYCoord, ArrayList<IField> possibleMoveList) {
        if (!validCoordinates(newFieldXCoord, newFieldYCoord)) {
            return;
        }

        IField field = board.getField(newFieldXCoord, newFieldYCoord);
        Figure figure = mediator.getFigure(field);

        if (figure == null) {
            possibleMoveList.add(field);
        }
    }

    private void addAttackField(final Color color, final int newFieldXCoord, final int newFieldYCoord, ArrayList<IField> possibleMoveList) {
        if (!validCoordinates(newFieldXCoord, newFieldYCoord)) {
            return;
        }

        IField field = board.getField(newFieldXCoord, newFieldYCoord);
        Figure figure = mediator.getFigure(field);

        if (figure != null && figure.getColor() != color) {
            possibleMoveList.add(field);
        }
    }

    private boolean validCoordinates(final int newFieldXCoord, final int newFieldYCoord) {
        return newFieldXCoord >= 0
                && newFieldXCoord < boardSize
                && newFieldYCoord >= 0
                && newFieldYCoord < boardSize;
    }
}
