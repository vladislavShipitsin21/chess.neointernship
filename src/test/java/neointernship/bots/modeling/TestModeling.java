package neointernship.bots.modeling;

import neointernship.chess.game.console.ConsoleBoardWriter;
import neointernship.chess.game.console.IConsoleBoardWriter;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.*;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.StoryGame;
import neointernship.web.client.GUI.Input.InputVoid;
import neointernship.web.client.player.APlayer;
import neointernship.web.client.player.FirstBot;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static neointernship.bots.InitGameMap.initGameMap;
import static org.junit.Assert.assertEquals;

public class TestModeling {

    @Test
    public void testStartPosition(){
        IMediator mediator = initGameMap();
        StoryGame storyGame = new StoryGame(mediator);

        IConsoleBoardWriter printer = new ConsoleBoardWriter(mediator,new Board());

        PossibleActionList list = new PossibleActionList(new Board(),mediator,storyGame);
        list.updateRealLists();

        Position startPosition = new Position(mediator,list);
        printer.printPosition(startPosition);

        Map<Position,IAnswer> resultPositions = Modeling.modeling(startPosition,Color.WHITE);

        for(Position position : resultPositions.keySet()) {
            printer.printPosition(position);
        }
        assertEquals(20,resultPositions.size());
    }
    @Test
    public void testTwoLevel(){
        IMediator mediator = new Mediator();
        StoryGame storyGame = new StoryGame(mediator);

        Figure figure = new King(Color.WHITE);
        IField field = new Field(0,0);
        mediator.addNewConnection(field,figure);

        Figure kNight = new Knight(Color.BLACK);
        IField fieldN = new Field(1,1);
        mediator.addNewConnection(fieldN,kNight);

        IConsoleBoardWriter printer = new ConsoleBoardWriter(mediator,new Board());

        PossibleActionList list = new PossibleActionList(new Board(),mediator,storyGame);
        list.updateRealLists();

        Position startPosition = new Position(mediator,list);
        printer.printPosition(startPosition);

        Map<Position,IAnswer> resultPositions = Modeling.modeling(startPosition,Color.WHITE);
        Map<Position,IAnswer> resultPositionsAll = new HashMap<>();

        for(Position position : resultPositions.keySet()) {
            printer.printPosition(position);
            resultPositionsAll.putAll(Modeling.modeling(position,Color.WHITE));
        }

        System.out.println("второй уровень");

        for(Position position : resultPositionsAll.keySet()) {
            printer.printPosition(position);
        }
        assertEquals(16,resultPositionsAll.size());
    }
    @Test
    public void testTwoLevelDifColor(){
        IMediator mediator = new Mediator();
        StoryGame storyGame = new StoryGame(mediator);

        Figure figure = new King(Color.WHITE);
        IField field = new Field(0,0);
        mediator.addNewConnection(field,figure);

        Figure kNight = new Knight(Color.BLACK);
        IField fieldN = new Field(1,1);
        mediator.addNewConnection(fieldN,kNight);

        IConsoleBoardWriter printer = new ConsoleBoardWriter(mediator,new Board());

        PossibleActionList list = new PossibleActionList(new Board(),mediator,storyGame);
        list.updateRealLists();

        Position startPosition = new Position(mediator,list);
        printer.printPosition(startPosition);

        Map<Position,IAnswer> resultPositions = Modeling.modeling(startPosition,Color.WHITE);
        Map<Position,IAnswer> resultPositionsAll = new HashMap<>();

        for(Position position : resultPositions.keySet()) {
            printer.printPosition(position);
            resultPositionsAll.putAll(Modeling.modeling(position,Color.BLACK));
        }

        System.out.println("второй уровень");

        for(Position position : resultPositionsAll.keySet()) {
            printer.printPosition(position);
        }
        assertEquals(8,resultPositionsAll.size());
    }

    @Test
    public void testTwoKingLevel2(){
        IMediator mediator = new Mediator();
        StoryGame storyGame = new StoryGame(mediator);

        Figure figureW = new King(Color.WHITE);
        IField fieldW = new Field(0,0);
        mediator.addNewConnection(fieldW,figureW);

        Figure figureB = new King(Color.BLACK);
        IField fieldB = new Field(7,7);
        mediator.addNewConnection(fieldB,figureB);

        IConsoleBoardWriter printer = new ConsoleBoardWriter(mediator,new Board());

        PossibleActionList list = new PossibleActionList(new Board(),mediator,storyGame);
        list.updateRealLists();

        Position startPosition = new Position(mediator,list);
        printer.printPosition(startPosition);

        Map<Position,IAnswer> resultPositions = Modeling.modeling(startPosition,Color.WHITE);
        Map<Position,IAnswer> resultPositionsAll = new HashMap<>();

        for(Position position : resultPositions.keySet()) {
            printer.printPosition(position);
            resultPositionsAll.putAll(Modeling.modeling(position,Color.BLACK));
        }

        System.out.println("второй уровень");

        for(Position position : resultPositionsAll.keySet()) {
            printer.printPosition(position);
        }
        assertEquals(9,resultPositionsAll.size());
    }
    @Test
    public void testTwoKingLevel3(){
        IMediator mediator = new Mediator();
        StoryGame storyGame = new StoryGame(mediator);

        Figure figureW = new King(Color.WHITE);
        IField fieldW = new Field(0,0);
        mediator.addNewConnection(fieldW,figureW);

        Figure figureB = new King(Color.BLACK);
        IField fieldB = new Field(7,7);
        mediator.addNewConnection(fieldB,figureB);

        IConsoleBoardWriter printer = new ConsoleBoardWriter(mediator,new Board());

        PossibleActionList list = new PossibleActionList(new Board(),mediator,storyGame);
        list.updateRealLists();

        Position startPosition = new Position(mediator,list);
        printer.printPosition(startPosition);

        Map<Position,IAnswer> resultPositionsOne = Modeling.modeling(startPosition,Color.WHITE);
        Map<Position,IAnswer> resultPositionsTwo = new HashMap<>();
        Map<Position,IAnswer> resultPositionsThree = new HashMap<>();

        for(Position position : resultPositionsOne.keySet()) {
            printer.printPosition(position);
            resultPositionsTwo.putAll(Modeling.modeling(position,Color.BLACK));
        }

        System.out.println("второй уровень");

        for(Position position : resultPositionsTwo.keySet()) {
            printer.printPosition(position);
            resultPositionsThree.putAll(Modeling.modeling(position,Color.WHITE));
        }

        System.out.println("третий уровень");

        for(Position position : resultPositionsThree.keySet()) {
            printer.printPosition(position);
        }

        assertEquals(27,resultPositionsThree.size());
    }
    @Test
    public void testTwoKingLevel4(){
        IMediator mediator = new Mediator();
        StoryGame storyGame = new StoryGame(mediator);

        Figure figureW = new King(Color.WHITE);
        IField fieldW = new Field(0,0);
        mediator.addNewConnection(fieldW,figureW);

        Figure figureB = new King(Color.BLACK);
        IField fieldB = new Field(7,7);
        mediator.addNewConnection(fieldB,figureB);

        IConsoleBoardWriter printer = new ConsoleBoardWriter(mediator,new Board());

        PossibleActionList list = new PossibleActionList(new Board(),mediator,storyGame);
        list.updateRealLists();

        Position startPosition = new Position(mediator,list);
        printer.printPosition(startPosition);

        Map<Position,IAnswer> resultPositionsOne = Modeling.modeling(startPosition,Color.WHITE);
        Map<Position,IAnswer> resultPositionsTwo = new HashMap<>();
        Map<Position,IAnswer> resultPositionsThree = new HashMap<>();
        Map<Position,IAnswer> resultPositionsFour = new HashMap<>();

        for(Position position : resultPositionsOne.keySet()) {
            printer.printPosition(position);
            resultPositionsTwo.putAll(Modeling.modeling(position,Color.BLACK));
        }

        System.out.println("второй уровень");

        for(Position position : resultPositionsTwo.keySet()) {
            printer.printPosition(position);
            resultPositionsThree.putAll(Modeling.modeling(position,Color.WHITE));
        }

        System.out.println("третий уровень");

        for(Position position : resultPositionsThree.keySet()) {
            printer.printPosition(position);
            resultPositionsFour.putAll(Modeling.modeling(position,Color.BLACK));
        }

        System.out.println("четвертый уровень");

        for(Position position : resultPositionsFour.keySet()) {
            printer.printPosition(position);
        }

        assertEquals(81,resultPositionsFour.size());
    }
    @Test
    public void testBot() throws InterruptedException {
        APlayer player = new FirstBot(Color.WHITE,"first bot",new InputVoid());
        IMediator mediator = new Mediator();

        Figure rook = new Rook(Color.WHITE);
        IField fieldR = new Field(0,0); // a8
        mediator.addNewConnection(fieldR,rook);

        Figure pawn = new Pawn(Color.BLACK);
        IField fieldPawn = new Field(7,0); // a1
        mediator.addNewConnection(fieldPawn,pawn);

        player.init(mediator,new Board(),Color.WHITE);

        String actual = player.getAnswer();
        String expected = "a8-a1";
        assertEquals(expected,actual);
    }
}
