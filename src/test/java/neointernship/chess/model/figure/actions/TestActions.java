package neointernship.chess.model.figure.actions;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.figure.actions.PossibleActionList;
import neointernship.chess.game.model.figure.piece.Bishop;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestActions {
    @Test
    public void test(){
        IPossibleActionList possibleActionList = new PossibleActionList();
        Figure figure = new Bishop(Color.BLACK);
        IField field = new Field(0,0);

        possibleActionList.addNewFigure(figure);

        Board board = new Board();
        Mediator mediator = new Mediator();

        mediator.addNewConnection(field,figure);

        possibleActionList.updateLists(figure,board,mediator);

        ArrayList<IField> resultList = (ArrayList<IField>) possibleActionList.getList(figure);

        
    }
}
