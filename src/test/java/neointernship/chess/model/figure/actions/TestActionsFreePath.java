package neointernship.chess.model.figure.actions;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.*;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestActionsFreePath {

    private void checkPosition(Figure figure,IField field,IField[] expected){

        Board board = new Board();
        Mediator mediator = new Mediator();

        mediator.addNewConnection(field,figure);

        IPossibleActionList possibleActionList = new PossibleActionList(board,mediator);

        possibleActionList.updateRealLists();

        List<IField> resultList = (List<IField>) possibleActionList.getRealList(figure);

        List<IField> expectedList = Arrays.stream(expected).collect(Collectors.toList());

        assertEquals(expectedList.size(),resultList.size());

        assertTrue(resultList.containsAll(expectedList));

    }

    @Test
    public void testKingCenter(){
        Figure figure = new King(Color.BLACK);
        IField field = new Field(2,2);

        Field[] expected = {
                new Field(1,1),new Field(1,2),new Field(1,3),
                new Field(2,1),                     new Field(2,3),
                new Field(3,1),new Field(3,2),new Field(3,3)
        };

        checkPosition(figure,field,expected);

    }
    @Test
    public void testBishop(){
        Figure figure = new Bishop(Color.BLACK);
        IField field = new Field(0,0);

        Field[] expected = {
                new Field(1,1),new Field(2,2),new Field(3,3),
                new Field(4,4),new Field(5,5),new Field(6,6),
                new Field(7,7)
        };

        checkPosition(figure,field,expected);

    }
    @Test
    public void testRook(){
        Figure figure = new Rook(Color.BLACK);
        IField field = new Field(4,4);

        Field[] expected = {
                new Field(4,0), new Field(4,1),new Field(4,2),new Field(4,3),
                new Field(4,5), new Field(4,6),new Field(4,7),

                new Field(0,4),new Field(1,4),new Field(2,4),
                new Field(3,4),new Field(5,4), new Field(6,4), new Field(7,4)
        };

        checkPosition(figure,field,expected);
    }
    @Test
    public void testPawnWhiteBegin(){
        Figure figure = new Pawn(Color.WHITE);
        IField field = new Field(6,4);

        Field[] expected = {
                new Field(5,4),new Field(4,4)
        };

        checkPosition(figure,field,expected);
    }
    @Test
    public void testPawnBlackBegin(){
        Figure figure = new Pawn(Color.BLACK);
        IField field = new Field(1,4);

        Field[] expected = {
                new Field(2,4),new Field(3,4)
        };

        checkPosition(figure,field,expected);
    }
    @Test
    public void testPawnBlackEnd(){
        Figure figure = new Pawn(Color.BLACK);
        IField field = new Field(3,4);

        Field[] expected = {
                new Field(4,4)
        };

        checkPosition(figure,field,expected);
    }
    @Test
    public void testPawnWhiteEnd(){
        Figure figure = new Pawn(Color.WHITE);
        IField field = new Field(3,4);

        Field[] expected = {
                new Field(2,4)
        };

        checkPosition(figure,field,expected);
    }
    @Test
    public void testKnight(){
        Figure figure = new Knight(Color.WHITE);
        IField field = new Field(0,2);

        Field[] expected = {
                new Field(1,0),new Field(1,4),new Field(2,1),new Field(2,3)
        };

        checkPosition(figure,field,expected);
    }

}
