package neointernship.chess.game.actions;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.StoryGame;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestStaticMethod {

    static void checkPosition(Figure figure, IField field, IField[] expected){

        Board board = new Board();
        Mediator mediator = new Mediator();

        mediator.addNewConnection(field,figure);

        IPossibleActionList possibleActionList = new PossibleActionList(board,mediator,new StoryGame(mediator));

        possibleActionList.updateRealLists();

        List<IField> resultList = (List<IField>) possibleActionList.getRealList(figure);

        List<IField> expectedList = Arrays.stream(expected).collect(Collectors.toList());

        assertEquals(expectedList.size(),resultList.size());

        assertTrue(resultList.containsAll(expectedList));

    }

    static void checkPosition(Map<Figure,IField> map, final Figure mainFigure,IField[] expected){

        final Board board = new Board();
        final IMediator mediator = new Mediator();

        for(Figure figure : map.keySet()){
            mediator.addNewConnection(map.get(figure),figure);
        }

        IPossibleActionList possibleActionList = new PossibleActionList(board,mediator,new StoryGame(mediator));

        possibleActionList.updateRealLists();

        List<IField> resultList = (List<IField>) possibleActionList.getRealList(mainFigure);

        List<IField> expectedList = Arrays.stream(expected).collect(Collectors.toList());

        assertEquals(expectedList.size(),resultList.size());

        assertTrue(resultList.containsAll(expectedList));
        assertTrue(expectedList.containsAll(resultList));

    }
}
