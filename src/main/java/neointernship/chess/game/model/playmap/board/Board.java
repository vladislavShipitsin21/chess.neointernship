package neointernship.chess.game.model.playmap.board;

import neointernship.chess.game.model.figure.Figure;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.field.IField;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Board implements IBoard {
    private final Mediator mediator;

    private final String chessType;

    private Field[][] fieldMatrix;

    public Board(final String chessType) {
        mediator = new Mediator();

        this.chessType = chessType;
    }

    @Override
    public void initializeBoard() {

    }

    @Override
    public IField getField(final int x, final int y) {
        return null;
    }

    @Override
    public IField getField(final Figure figure) {
        return null;
    }

    @Override
    public Figure getFigure(final IField field) {
        return null;
    }

    @Override
    public ArrayList<Figure> getFigures() {
        return null;
    }
}
