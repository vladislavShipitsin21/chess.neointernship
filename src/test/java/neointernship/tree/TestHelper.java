package neointernship.tree;

import junit.framework.TestCase;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.StoryGame;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static neointernship.bots.InitGameMap.initGameMap;
import static org.junit.Assert.assertEquals;

public class TestHelper {

    @Test
    public void testLevel1(){
        IMediator mediator = initGameMap();
        StoryGame storyGame = new StoryGame(mediator);

        PossibleActionList list = new PossibleActionList(new Board(),mediator,storyGame);
        list.updateRealLists();

        Position startPosition = new Position(mediator,list);

        BuilderTree builderTree = new BuilderTree(1, Color.WHITE);

        INode root = builderTree.getTree(startPosition);

        Collection<INode> result = HelperBuilderTree.getChildren(root);

        assertEquals(20,result.size());
    }
    @Test
    public void testLevel2(){
        IMediator mediator = initGameMap();
        StoryGame storyGame = new StoryGame(mediator);

        PossibleActionList list = new PossibleActionList(new Board(),mediator,storyGame);
        list.updateRealLists();

        Position startPosition = new Position(mediator,list);

        BuilderTree builderTree = new BuilderTree(2, Color.WHITE);

        INode root = builderTree.getTree(startPosition);

        Collection<INode> result = HelperBuilderTree.getAllChildren(root);

        assertEquals(400,result.size());
    }
    @Test
    public void testLevel3(){
        IMediator mediator = initGameMap();
        StoryGame storyGame = new StoryGame(mediator);

        PossibleActionList list = new PossibleActionList(new Board(),mediator,storyGame);
        list.updateRealLists();

        Position startPosition = new Position(mediator,list);

        BuilderTree builderTree = new BuilderTree(3, Color.WHITE);

        INode root = builderTree.getTree(startPosition);

        Collection<INode> result = HelperBuilderTree.getAllChildren(root);

        assertEquals(400,result.size());
    }

    @Test
    public void testTwoKing(){
        Map<Integer,Integer> levelExpectedMap = new HashMap<>();
        levelExpectedMap.put(1,3);
        levelExpectedMap.put(2,9);
        levelExpectedMap.put(3,27);
        levelExpectedMap.put(4,81);

        for(Integer level : levelExpectedMap.keySet()){
            final int expected = levelExpectedMap.get(level);
            final int actual = twoKing(level);
            TestCase.assertEquals(expected,actual);
        }
    }
    private int twoKing(final int level) {
        IMediator mediator = new Mediator();
        StoryGame storyGame = new StoryGame(mediator);

        Figure figureW = new King(Color.WHITE);
        IField fieldW = new Field(0, 0);
        mediator.addNewConnection(fieldW, figureW);

        Figure figureB = new King(Color.BLACK);
        IField fieldB = new Field(7, 7);
        mediator.addNewConnection(fieldB, figureB);

        PossibleActionList list = new PossibleActionList(new Board(), mediator, storyGame);
        list.updateRealLists();

        Position startPosition = new Position(mediator, list);

        BuilderTree builderTree = new BuilderTree(level, Color.WHITE);

        INode root = builderTree.getTree(startPosition);

        Collection<INode> result = HelperBuilderTree.getAllChildren(root);

        return result.size();
    }
}
