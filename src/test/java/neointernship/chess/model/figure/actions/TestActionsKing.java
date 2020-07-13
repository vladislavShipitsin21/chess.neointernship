package neointernship.chess.model.figure.actions;

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
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestActionsKing {

    // todo сделать тест, которому на вход фигура и его позиция , а также ожидаемый список полей
    @Test
    public void testFreePathCenter(){
        Figure figure = new King(Color.BLACK);
        IField field = new Field(2,2);

        Board board = new Board();
        Mediator mediator = new Mediator();

        mediator.addNewConnection(field,figure);

        IPossibleActionList possibleActionList = new PossibleActionList(board,mediator);

        possibleActionList.addNewFigure(figure);

        possibleActionList.updateLists();
        List<IField> resultList = (List<IField>) possibleActionList.getList(figure);

        Field[] expected = {
                new Field(1,1),new Field(1,2),new Field(1,3),
                new Field(2,1),                     new Field(2,3),
                new Field(3,1),new Field(3,2),new Field(3,3)
        };

        List<Field> expectedList = Arrays.stream(expected).collect(Collectors.toList());

        assertEquals(expectedList.size(),resultList.size());

        assertTrue(resultList.containsAll(expectedList));
    }
}
