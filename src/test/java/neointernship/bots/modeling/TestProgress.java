package neointernship.bots.modeling;

import neointernship.chess.game.console.ConsoleBoardWriter;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.AnswerSimbol;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.*;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.StoryGame;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static neointernship.bots.InitGameMap.initGameMap;
import static neointernship.chess.game.model.enums.Color.WHITE;

public class TestProgress {


    private IAnswer twoPawnAttack(int level){
        IMediator mediator = new Mediator();
        StoryGame storyGame = new StoryGame(mediator);

        Figure figureBishop = new Bishop(WHITE);
        IField fieldWB = new Field(7,7);
        mediator.addNewConnection(fieldWB,figureBishop);

        Figure figureB1 = new Pawn(Color.BLACK);
        IField fieldB1 = new Field(4,4);
        mediator.addNewConnection(fieldB1,figureB1);

        Figure figureB2 = new Pawn(Color.BLACK);
        IField fieldB2 = new Field(3,5);
        mediator.addNewConnection(fieldB2,figureB2);


        PossibleActionList list = new PossibleActionList(new Board(),mediator,storyGame);
        list.updateRealLists();

        Position startPosition = new Position(mediator,list);

        return Progressing.getSolution(startPosition,WHITE,level);
    }

    @Test
    public void testTransformete(){
        Map<Integer,IAnswer> levelExpectedMap = new HashMap<>();
        levelExpectedMap.put(1,new AnswerSimbol(1,0,0,0,'Q'));
//        levelExpectedMap.put(2,new Answer(7,7,6,6,'Q'));
//        levelExpectedMap.put(3,3.);
//        levelExpectedMap.put(4,1.);

        for(Integer level : levelExpectedMap.keySet()){
            final IAnswer expected = levelExpectedMap.get(level);
            final IAnswer actual = transformate(level);
            assertEquals(expected,actual);
        }
    }
    private IAnswer transformate(int level){
        IMediator mediator = new Mediator();
        StoryGame storyGame = new StoryGame(mediator);

        Figure figureW = new Pawn(WHITE);
        IField fieldW = new Field(1,0);
        mediator.addNewConnection(fieldW,figureW);

        PossibleActionList list = new PossibleActionList(new Board(),mediator,storyGame);
        list.updateRealLists();

        Position startPosition = new Position(mediator,list);

        return Progressing.getSolution(startPosition,WHITE,level);
    }


    @Test
    public void testMateLevel2(){
        IMediator mediator = new Mediator();
        StoryGame storyGame = new StoryGame(mediator);

        Figure whiteKing = new King(WHITE);
        IField fieldWhiteKing = new Field(0,3);
        mediator.addNewConnection(fieldWhiteKing,whiteKing);

        Figure whiteQueen = new Rook(WHITE);
        IField fieldQ = new Field(7,1);
        mediator.addNewConnection(fieldQ,whiteQueen);

        Figure blackKing = new King(Color.BLACK);
        IField fieldBlackKing = new Field(0,0);
        mediator.addNewConnection(fieldBlackKing,blackKing);


        PossibleActionList list = new PossibleActionList(new Board(),mediator,storyGame);
        list.updateRealLists();

        Position startPosition = new Position(mediator,list);

        IAnswer actual =  Progressing.getSolution(startPosition,WHITE,4);

        IAnswer expected = new AnswerSimbol(0,3,1,2,'Q');

        ConsoleBoardWriter.printAnswer(actual);

        assertEquals(expected,actual);
    }
    @Test
    public void testMateLevel1(){
        IMediator mediator = new Mediator();
        StoryGame storyGame = new StoryGame(mediator);

        Figure whiteKing = new King(WHITE);
        IField fieldWhiteKing = new Field(2,2);
        mediator.addNewConnection(fieldWhiteKing,whiteKing);

        Figure whiteQueen = new Queen(WHITE);
        IField fieldQ = new Field(7,1);
        mediator.addNewConnection(fieldQ,whiteQueen);

        Figure blackKing = new King(Color.BLACK);
        IField fieldBlackKing = new Field(0,0);
        mediator.addNewConnection(fieldBlackKing,blackKing);


        PossibleActionList list = new PossibleActionList(new Board(),mediator,storyGame);
        list.updateRealLists();

        Position startPosition = new Position(mediator,list);

        IAnswer answer =  Progressing.getSolution(startPosition,WHITE,1);

        ConsoleBoardWriter.printAnswer(answer);
    }
    @Test
    public void testMateRookLevel1(){
        IMediator mediator = new Mediator();
        StoryGame storyGame = new StoryGame(mediator);

        Figure whiteKing = new King(WHITE);
        IField fieldWhiteKing = new Field(1,2);
        mediator.addNewConnection(fieldWhiteKing,whiteKing);

        Figure whiteQueen = new Rook(WHITE);
        IField fieldQ = new Field(7,1);
        mediator.addNewConnection(fieldQ,whiteQueen);

        Figure blackKing = new King(Color.BLACK);
        IField fieldBlackKing = new Field(0,0);
        mediator.addNewConnection(fieldBlackKing,blackKing);


        PossibleActionList list = new PossibleActionList(new Board(),mediator,storyGame);
        list.updateRealLists();

        Position startPosition = new Position(mediator,list);

        IAnswer answer =  Progressing.getSolution(startPosition,WHITE,1);

        ConsoleBoardWriter.printAnswer(answer);
        IAnswer expected = new AnswerSimbol(7,1,7,0,'Q');

        assertEquals(expected,answer);
    }
}
