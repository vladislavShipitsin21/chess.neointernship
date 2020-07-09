package neointernship.chess.model.figure.factory;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.factory.Factory;
import neointernship.chess.game.model.figure.factory.IFactory;
import neointernship.chess.game.model.figure.piece.Bishop;
import neointernship.chess.game.model.figure.piece.Figure;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestFactoty {
    @Test
    public void testCreateBishop(){

        IFactory factory = new Factory();
        Figure figure = factory.createFigure('B', Color.WHITE);

        assertEquals(figure.getClass(), Bishop.class);
        Bishop bishop = (Bishop) figure;

        assertEquals(bishop.getName(),"Bishop");
        assertEquals(bishop.getGameSymbol(),'B');
        assertEquals(bishop.getColor(),Color.WHITE);
        assertEquals(bishop.getPrice(),(short) 3);

    }
}
