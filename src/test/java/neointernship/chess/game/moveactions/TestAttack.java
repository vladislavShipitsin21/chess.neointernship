package neointernship.chess.game.moveactions;

import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.Rook;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.communication.message.TurnStatus;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestAttack extends TestAllowCommand {
    @Test
    public void testAttack() {
        final Figure figureW = new Rook(Color.WHITE);
        final IField fieldW = new Field(0, 0);

        final Figure figureB = new Rook(Color.BLACK);
        final IField fieldB = new Field(0, 6);

        addFigure(fieldW, figureW);
        addFigure(fieldB, figureB);

        assertEquals(2, mediator.getFigures().size());

        final IAnswer answer = new Answer(0, 0, 0, 6, 'Q');

        final TurnStatus result = allowMoveCommand.execute(answer);

        assertEquals(TurnStatus.ATTACK, result);

        assertEquals(1, mediator.getFigures().size());

        final IField fieldRookExpected = new Field(0, 6);
        assertEquals(fieldRookExpected, mediator.getField(figureW));

        assertNull(mediator.getField(figureB));
    }
}
