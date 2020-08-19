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

public class TestCheck {
    private Map<IField, Figure> fieldFigureMap;
    private TestHeadEnd testHeadEnd;

    @Before
    public void testBefore() {
        fieldFigureMap = new HashMap<>();
    }

    /**
     * попытка сделать ход под шахом, которая приводит к неудаче.
     */
    @Test
    public void testCheck() {

        final Figure kingW = new King(Color.WHITE);
        final IField fieldKingW = new Field(0, 0);
        fieldFigureMap.put(fieldKingW, kingW);

        final Figure kingB = new King(Color.BLACK);
        final IField fieldKingB = new Field(7, 7);
        fieldFigureMap.put(fieldKingB, kingB);

        final Figure rook1 = new Rook(Color.WHITE);
        final IField fieldRook1 = new Field(6, 5);
        fieldFigureMap.put(fieldRook1, rook1);


        testHeadEnd = new TestHeadEnd(fieldFigureMap);

        final IAnswer answer1 = new Answer(6, 5, 7, 5, 'Q');

        assertEquals(EnumGameState.ALIVE, testHeadEnd.getState().getValue());

        testHeadEnd.doAllowIteration(answer1);

        final IAnswer answer2 = new Answer(7, 7, 7, 6, 'Q');

        testHeadEnd.doRestringIteration(answer2);

    }

}
