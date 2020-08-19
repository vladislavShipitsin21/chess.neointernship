package neointernship.chess.game.end;

import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.figure.piece.Rook;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestDrawRepetitionPosition {
    private Map<IField, Figure> fieldFigureMap;
    private Map<Integer, IAnswer> answerMap;
    private TestHeadEnd testHeadEnd;

    @Before
    public void testBefore() {
        fieldFigureMap = new HashMap<>();
        answerMap = new HashMap<>();
    }

    @Test
    public void testCorrect() {
        final Figure kingW = new King(Color.WHITE);
        final IField fieldKingW = new Field(0, 0);
        fieldFigureMap.put(fieldKingW, kingW);

        final Figure rookW = new Rook(Color.WHITE);
        final IField fieldRookW = new Field(1, 1);
        fieldFigureMap.put(fieldRookW, rookW);

        final Figure kingB = new King(Color.BLACK);
        final IField fieldKingB = new Field(7, 7);
        fieldFigureMap.put(fieldKingB, kingB);

        testHeadEnd = new TestHeadEnd(fieldFigureMap);

        final IAnswer answerW1 = new Answer(0, 0, 0, 1, 'Q');
        final IAnswer answerW2 = new Answer(0, 1, 0, 0, 'Q');

        final IAnswer answerB1 = new Answer(7, 7, 6, 6, 'Q');
        final IAnswer answerB2 = new Answer(6, 6, 7, 7, 'Q');

        answerMap.put(1, answerW1);
        answerMap.put(2, answerB1);
        answerMap.put(3, answerW2);
        answerMap.put(4, answerB2);
        // позиция повторилась второй раз
        answerMap.put(5, answerW1);
        answerMap.put(6, answerB1);
        answerMap.put(7, answerW2);
        answerMap.put(8, answerB2);

        assertEquals(EnumGameState.ALIVE, testHeadEnd.getState().getValue());

        testHeadEnd.doIterations(answerMap);

        assertEquals(EnumGameState.ALIVE, testHeadEnd.getState().getValue());

        // ход приводящий к 3х кратному повторению позиции
        testHeadEnd.doAllowIteration(answerW1);

        assertEquals(EnumGameState.DRAW_REPETITION_POSITION, testHeadEnd.getState().getValue());
        assertEquals(Color.BOTH, testHeadEnd.getState().getColor());
    }

    @Test
    public void testInvalid() {
        final Figure kingW = new King(Color.WHITE);
        final IField fieldKingW = new Field(7, 4);
        fieldFigureMap.put(fieldKingW, kingW);

        final Figure rookW = new Rook(Color.WHITE);
        final IField fieldRookW = new Field(7, 7);
        fieldFigureMap.put(fieldRookW, rookW);

        final Figure kingB = new King(Color.BLACK);
        final IField fieldKingB = new Field(0, 0);
        fieldFigureMap.put(fieldKingB, kingB);

        testHeadEnd = new TestHeadEnd(fieldFigureMap);

        final IAnswer answerW1 = new Answer(7, 4, 7, 5, 'Q');
        final IAnswer answerW2 = new Answer(7, 5, 7, 4, 'Q');

        final IAnswer answerB1 = new Answer(0, 0, 0, 1, 'Q');
        final IAnswer answerB2 = new Answer(0, 1, 0, 0, 'Q');

        answerMap.put(1, answerB1);
        answerMap.put(2, answerW1);
        answerMap.put(3, answerB2);
        answerMap.put(4, answerW2);
        // позиция повторилась второй раз
        answerMap.put(5, answerB1);
        answerMap.put(6, answerW1);
        answerMap.put(7, answerB2);
        answerMap.put(8, answerW2);

        assertEquals(EnumGameState.ALIVE, testHeadEnd.getState().getValue());

        testHeadEnd.updateColor();
        testHeadEnd.doIterations(answerMap);

        assertEquals(EnumGameState.ALIVE, testHeadEnd.getState().getValue());

        testHeadEnd.doAllowIteration(answerB1);

        assertEquals(EnumGameState.ALIVE, testHeadEnd.getState().getValue());
    }

}
