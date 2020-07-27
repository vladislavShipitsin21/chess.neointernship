package neointernship.chess.game.moveactions;

import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.Pawn;
import neointernship.chess.game.model.figure.piece.Rook;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.communication.message.TurnStatus;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestAisleTake extends TestAllowCommand {
    @Test
    public void testWhite(){
        Figure figureW = new Pawn(Color.WHITE);
        IField fieldW = new Field(3,4);

        Figure figureB = new Pawn(Color.BLACK);
        IField fieldB = new Field(3,3);

        addFigure(fieldW,figureW);
        addFigure(fieldB,figureB);

        assertEquals(2,mediator.getFigures().size());

        IAnswer answer = new Answer(3,4,2,3,'Q');

        TurnStatus result = allowMoveCommand.execute(figureW.getColor(),answer);

        assertEquals(TurnStatus.AISLE_TAKE,result);

        assertEquals(1,mediator.getFigures().size());

        IField fieldPawnWExpected = new Field(2,3);
        assertEquals(fieldPawnWExpected,mediator.getField(figureW));

        assertNull(mediator.getField(figureB));
    }
    @Test
    public void testBlack(){
        Figure figureW = new Pawn(Color.WHITE);
        IField fieldW = new Field(4,4);

        Figure figureB = new Pawn(Color.BLACK);
        IField fieldB = new Field(4,3);

        addFigure(fieldW,figureW);
        addFigure(fieldB,figureB);

        assertEquals(2,mediator.getFigures().size());

        IAnswer answer = new Answer(4,4,5,3,'Q');

        TurnStatus result = allowMoveCommand.execute(figureW.getColor(),answer);

        assertEquals(TurnStatus.AISLE_TAKE,result);

        assertEquals(1,mediator.getFigures().size());

        IField fieldPawnWExpected = new Field(5,3);
        assertEquals(fieldPawnWExpected,mediator.getField(figureW));

        assertNull(mediator.getField(figureB));
    }
}
