package neointernship.chess.model.mediator;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Bishop;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.figure.piece.Queen;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestMediator {

    private static Mediator mediator;
    private static IField fieldB;
    private static IField fieldQ;
    private static Figure figureB;
    private static Figure figureQ;

    @BeforeClass
    public static void init(){
        mediator = new Mediator();

        fieldB = new Field(0,0);
        fieldQ = new Field(5,5);

        figureB = new Bishop(Color.BLACK);
        figureQ = new Queen(Color.WHITE);

        mediator.addNewConnection(fieldB,figureB);
        mediator.addNewConnection(fieldQ,figureQ);
    }

    @AfterClass
    public static void clear(){
        mediator.clear();
    }

    @Test
    public void testGetFigure(){
        Figure result = mediator.getFigure(fieldB);

        assertEquals(figureB,result);
    }
    @Test
    public void testGetFigures(){
        Collection<Figure> figures = mediator.getFigures();

        assertEquals(figures.size(),2);
        assertTrue(figures.contains(figureB));
        assertTrue(figures.contains(figureQ));
    }
    @Test
    public void testGetField(){
        IField result = mediator.getField(figureQ);

        assertEquals(fieldQ,result);
    }
    @Test
    public void testGetKing(){
        Color color = Color.WHITE;
        King king = new King(color);
        IField fieldK = new Field(1,1);

        mediator.addNewConnection(fieldK,king);

        Figure resultFigure = mediator.getKing(color);
        IField resultField = mediator.getField(resultFigure);

        assertEquals(resultFigure,king);
        assertEquals(resultField,fieldK);

    }
}

