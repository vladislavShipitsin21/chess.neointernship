package neointernship.chess.model.figure.actions;

import javafx.print.Collation;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.figure.actions.PossibleActionList;
import neointernship.chess.game.model.figure.piece.Bishop;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestActionsBishop {
    @BeforeClass
    public void before(){

    }

    @Test
    public void testFreePath(){
        Figure figure = new Bishop(Color.BLACK);
        IField field = new Field(0,0);

        Board board = new Board();
        Mediator mediator = new Mediator();

        mediator.addNewConnection(field,figure);

        IPossibleActionList possibleActionList = new PossibleActionList(board,mediator);

        possibleActionList.addNewFigure(figure);

        possibleActionList.updateLists();

        List<IField> resultList = (List<IField>) possibleActionList.getList(figure);

        Field[] expected = {
                new Field(1,1),new Field(2,2),new Field(3,3),
                new Field(4,4),new Field(5,5),new Field(6,6),
                new Field(7,7)
        };

        List<Field> expectedList = Arrays.stream(expected).collect(Collectors.toList());

        assertEquals(expectedList.size(),resultList.size());

        assertTrue(resultList.containsAll(expectedList));

    }
    @Test
    public void testClosePath(){
        Figure figureB = new Bishop(Color.BLACK);
        Figure figureK = new King(Color.WHITE);
        IField fieldB = new Field(0,0);
        IField fieldK = new Field(2,2);

        Board board = new Board();
        Mediator mediator = new Mediator();

        mediator.addNewConnection(fieldB,figureB);
        mediator.addNewConnection(fieldK,figureK);

        IPossibleActionList possibleActionList = new PossibleActionList(board,mediator);

        possibleActionList.addNewFigure(figureB);
        possibleActionList.addNewFigure(figureK);

        possibleActionList.updateLists();

        Collection<IField> resultList = possibleActionList.getList(figureB);

        Field[] expected = { new Field(1,1),new Field(2,2) };

        List<Field> expectedList = Arrays.stream(expected).collect(Collectors.toList());

        assertEquals(expectedList.size(),resultList.size());

        assertTrue(resultList.containsAll(expectedList));

    }
}
