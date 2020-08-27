package neointernship.chess.game.actions;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.Pawn;
import neointernship.chess.game.model.figure.piece.Rook;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static neointernship.chess.game.actions.TestStaticMethod.checkPosition;

public class TestActionsClosePath {
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
    public void testPawnBeginFriend() {
        final Figure pawn = new Pawn(Color.WHITE);
        final IField fieldPawn = new Field(6, 4);

        final Figure rook = new Rook(Color.WHITE);
        final IField fieldRook = new Field(5, 4);

        final Field[] expected = {};

        put(pawn, fieldPawn);
        put(rook, fieldRook);

        checkPosition(map, pawn, expected);
    }

    @Test
    public void testPawnBeginEntry() {
        final Figure pawn = new Pawn(Color.WHITE);
        final IField fieldPawn = new Field(6, 4);

        final Figure rook = new Rook(Color.BLACK);
        final IField fieldRook = new Field(5, 4);

        final Field[] expected = {};

        put(pawn, fieldPawn);
        put(rook, fieldRook);

        checkPosition(map, pawn, expected);
    }

    @Test
    public void testPawnBegin() {
        final Figure pawn = new Pawn(Color.WHITE);
        final IField fieldPawn = new Field(6, 4);

        final Figure rook = new Rook(Color.BLACK);
        final IField fieldRook = new Field(4, 4);

        final Field[] expected = {new Field(5, 4)};

        put(pawn, fieldPawn);
        put(rook, fieldRook);

        checkPosition(map, pawn, expected);
    }

}
