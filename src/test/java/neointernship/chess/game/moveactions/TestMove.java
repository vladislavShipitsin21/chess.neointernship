package neointernship.chess.game.moveactions;

import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.communication.message.TurnStatus;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMove extends TestAllowCommand {

    @Test
    public void testMove() {

        final Figure figure = new King(Color.WHITE);
        final IField field = new Field(0, 0);

        addFigure(field, figure);

        final IAnswer answer = new Answer(0, 0, 1, 1, 'Q');

        final TurnStatus result = allowMoveCommand.execute(answer);

        assertEquals(TurnStatus.MOVE, result);

        final IField fieldKingExpected = new Field(1, 1);

        assertEquals(fieldKingExpected, mediator.getField(figure));
    }
}
