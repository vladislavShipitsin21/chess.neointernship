package neointernship.bots.modeling;

import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.figure.piece.Pawn;
import neointernship.chess.game.model.figure.piece.Rook;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.StoryGame;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static neointernship.bots.InitGameMap.initGameMap;
import static org.junit.Assert.assertEquals;

public class TestProgress {

    @Test
    public void testTwoPawnBlock(){
        Map<Integer,Integer> levelExpectedMap = new HashMap<>();
        levelExpectedMap.put(1,2);
        levelExpectedMap.put(2,4);
        levelExpectedMap.put(3,3);
        levelExpectedMap.put(4,1);

        for(Integer level : levelExpectedMap.keySet()){
            final int expected = levelExpectedMap.get(level);
            final int actual = twoPawnBlock(level);
            assertEquals(expected,actual);
        }
    }
    private int twoPawnBlock(int level){
        IMediator mediator = new Mediator();
        StoryGame storyGame = new StoryGame(mediator);

        Figure figureW = new Pawn(Color.WHITE);
        IField fieldW = new Field(6,0);
        mediator.addNewConnection(fieldW,figureW);

        Figure figureB = new Pawn(Color.BLACK);
        IField fieldB = new Field(1,0);
        mediator.addNewConnection(fieldB,figureB);

        PossibleActionList list = new PossibleActionList(new Board(),mediator,storyGame);
        list.updateRealLists();

        Position startPosition = new Position(mediator,list);

        return Progressing.progress(startPosition,level);
    }
    @Test
    public void testTwoPawnFree(){
        Map<Integer,Integer> levelExpectedMap = new HashMap<>();
        /*levelExpectedMap.put(1,2);
        levelExpectedMap.put(2,4);*/
        levelExpectedMap.put(3,4);
//        levelExpectedMap.put(4,1);*/

        for(Integer level : levelExpectedMap.keySet()){
            final int expected = levelExpectedMap.get(level);
            final int actual = twoPawnFree(level);
            assertEquals(expected,actual);
        }
    }
    private int twoPawnFree(int level){
        IMediator mediator = new Mediator();
        StoryGame storyGame = new StoryGame(mediator);

        Figure figureW = new Pawn(Color.WHITE);
        IField fieldW = new Field(6,0);
        mediator.addNewConnection(fieldW,figureW);

        Figure figureB = new Pawn(Color.BLACK);
        IField fieldB = new Field(1,7);
        mediator.addNewConnection(fieldB,figureB);

        PossibleActionList list = new PossibleActionList(new Board(),mediator,storyGame);
        list.updateRealLists();

        Position startPosition = new Position(mediator,list);

        return Progressing.progress(startPosition,level);
    }


    @Test
    public void testTwoRook(){
        Map<Integer,Integer> levelExpectedMap = new HashMap<>();
        levelExpectedMap.put(1,14);
        levelExpectedMap.put(2,196);
        /*levelExpectedMap.put(3,27);
        levelExpectedMap.put(4,81);*/

        for(Integer level : levelExpectedMap.keySet()){
            final int expected = levelExpectedMap.get(level);
            final int actual = twoRook(level);
            assertEquals(expected,actual);
        }
    }
    private int twoRook(int level){
        IMediator mediator = new Mediator();
        StoryGame storyGame = new StoryGame(mediator);

        Figure figureW = new Rook(Color.WHITE);
        IField fieldW = new Field(0,0);
        mediator.addNewConnection(fieldW,figureW);

        Figure figureB = new Rook(Color.BLACK);
        IField fieldB = new Field(7,7);
        mediator.addNewConnection(fieldB,figureB);

        PossibleActionList list = new PossibleActionList(new Board(),mediator,storyGame);
        list.updateRealLists();

        Position startPosition = new Position(mediator,list);

        return Progressing.progress(startPosition,level);
    }

    @Test
    public void testTwoKing(){
        Map<Integer,Integer> levelExpectedMap = new HashMap<>();
        levelExpectedMap.put(1,3);
        levelExpectedMap.put(2,9);
        levelExpectedMap.put(3,27);
        levelExpectedMap.put(4,81);

        for(Integer level : levelExpectedMap.keySet()){
            if(level == 4){
                System.out.println();
            }
            final int expected = levelExpectedMap.get(level);
            final int actual = testTwoKing(level);
            assertEquals(expected,actual);
        }
    }
    private int testTwoKing(int level){
        IMediator mediator = new Mediator();
        StoryGame storyGame = new StoryGame(mediator);

        Figure figureW = new King(Color.WHITE);
        IField fieldW = new Field(0,0);
        mediator.addNewConnection(fieldW,figureW);

        Figure figureB = new King(Color.BLACK);
        IField fieldB = new Field(7,7);
        mediator.addNewConnection(fieldB,figureB);

        PossibleActionList list = new PossibleActionList(new Board(),mediator,storyGame);
        list.updateRealLists();

        Position startPosition = new Position(mediator,list);

        return Progressing.progress(startPosition,level);
    }

    @Test
    public void testStartPositionLevel2(){
        IMediator mediator = initGameMap();

        StoryGame storyGame = new StoryGame(mediator);

        PossibleActionList list = new PossibleActionList(new Board(),mediator,storyGame);
        list.updateRealLists();

        Position startPosition = new Position(mediator,list);

        int result = Progressing.progress(startPosition,2);

        assertEquals(400,result);
    }
}
