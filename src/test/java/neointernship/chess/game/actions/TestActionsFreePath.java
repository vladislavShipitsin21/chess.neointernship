package neointernship.chess.game.actions;


import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.*;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static neointernship.chess.game.actions.TestStaticMethod.checkPosition;


public class TestActionsFreePath {
    private static Map<Figure, IField> map;

    @BeforeClass
    public static void before() {
        map = new HashMap<>();
    }

    @After
    public void clear() {
        map.clear();
    }

    private void put(final Figure figure, final IField field) {
        map.put(figure, field);
    }

    @Test
    public void testKingCenter() {
        final Figure figure = new King(Color.BLACK);
        final IField field = new Field(2, 2);


        final Field[] expected = {
                new Field(1, 1), new Field(1, 2), new Field(1, 3),
                new Field(2, 1), new Field(2, 3),
                new Field(3, 1), new Field(3, 2), new Field(3, 3)
        };
        put(figure, field);
        checkPosition(map, figure, expected);

    }

    @Test
    public void testBishop() {
        final Figure figure = new Bishop(Color.BLACK);
        final IField field = new Field(0, 0);

        final Field[] expected = {
                new Field(1, 1), new Field(2, 2), new Field(3, 3),
                new Field(4, 4), new Field(5, 5), new Field(6, 6),
                new Field(7, 7)
        };

        put(figure, field);
        checkPosition(map, figure, expected);

    }

    @Test
    public void testRook() {
        final Figure figure = new Rook(Color.BLACK);
        final IField field = new Field(4, 4);

        final Field[] expected = {
                new Field(4, 0), new Field(4, 1), new Field(4, 2), new Field(4, 3),
                new Field(4, 5), new Field(4, 6), new Field(4, 7),

                new Field(0, 4), new Field(1, 4), new Field(2, 4),
                new Field(3, 4), new Field(5, 4), new Field(6, 4), new Field(7, 4)
        };

        put(figure, field);
        checkPosition(map, figure, expected);
    }

    @Test
    public void testPawnWhiteBegin() {
        final Figure figure = new Pawn(Color.WHITE);
        final IField field = new Field(6, 4);

        final Field[] expected = {
                new Field(5, 4), new Field(4, 4)
        };

        put(figure, field);
        checkPosition(map, figure, expected);
    }

    @Test
    public void testPawnBlackBegin() {
        final Figure figure = new Pawn(Color.BLACK);
        final IField field = new Field(1, 4);

        final Field[] expected = {
                new Field(2, 4), new Field(3, 4)
        };

        put(figure, field);
        checkPosition(map, figure, expected);
    }

    @Test
    public void testPawnBlackCenter() {
        final Figure figure = new Pawn(Color.BLACK);
        final IField field = new Field(3, 4);

        final Field[] expected = {
                new Field(4, 4)
        };

        put(figure, field);
        checkPosition(map, figure, expected);
    }

    @Test
    public void testPawnWhiteCenter() {
        final Figure figure = new Pawn(Color.WHITE);
        final IField field = new Field(4, 4);

        final Field[] expected = {
                new Field(3, 4)
        };

        put(figure, field);
        checkPosition(map, figure, expected);
    }

    @Test
    public void testPawnWhiteEnd() {
        final Figure figure = new Pawn(Color.WHITE);
        final IField field = new Field(1, 4);

        final Field[] expected = {
                new Field(0, 4)
        };

        put(figure, field);
        checkPosition(map, figure, expected);
    }

    @Test
    public void testPawnBlackEnd() {
        final Figure figure = new Pawn(Color.BLACK);
        final IField field = new Field(6, 4);

        final Field[] expected = {
                new Field(7, 4)
        };

        put(figure, field);
        checkPosition(map, figure, expected);
    }

    @Test
    public void testKnight() {
        final Figure figure = new Knight(Color.WHITE);
        final IField field = new Field(0, 2);

        final Field[] expected = {
                new Field(1, 0), new Field(1, 4), new Field(2, 1), new Field(2, 3)
        };

        put(figure, field);
        checkPosition(map, figure, expected);
    }

}
