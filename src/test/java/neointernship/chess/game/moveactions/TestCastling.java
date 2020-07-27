package neointernship.chess.game.moveactions;

import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.figure.piece.Rook;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.communication.message.ChessCodes;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCastling extends TestAllowCommand {
    @Test
    public void testCastling(){
        Figure king = new King(Color.WHITE);
        IField fieldW = new Field(7,4);

        Figure rook = new Rook(Color.WHITE);
        IField fieldB = new Field(7,7);

        addFigure(fieldW,king);
        addFigure(fieldB,rook);

        IAnswer answer = new Answer(7,4,7,6,'Q');

        ChessCodes result = allowMoveCommand.execute(king.getColor(),answer);

        assertEquals(ChessCodes.CASTLING,result);

        IField fieldKingExpected = new Field(7,6);
        IField fieldRookExpected = new Field(7,5);

        assertEquals(fieldKingExpected,mediator.getField(king));
        assertEquals(fieldRookExpected,mediator.getField(rook));
    }
}
