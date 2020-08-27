package neointernship.bots.modeling;

import neointernship.chess.game.console.ConsoleBoardWriter;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
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
import static neointernship.chess.game.model.enums.Color.WHITE;

public class TestProgress {


    private IAnswer twoPawnAttack(final int level) {
        final IMediator mediator = new Mediator();
        final StoryGame storyGame = new StoryGame(mediator);

        final Figure figureBishop = new Bishop(WHITE);
        final IField fieldWB = new Field(7, 7);
        mediator.addNewConnection(fieldWB, figureBishop);

        final Figure figureB1 = new Pawn(Color.BLACK);
        final IField fieldB1 = new Field(4, 4);
        mediator.addNewConnection(fieldB1, figureB1);

        final Figure figureB2 = new Pawn(Color.BLACK);
        final IField fieldB2 = new Field(3, 5);
        mediator.addNewConnection(fieldB2, figureB2);


        final PossibleActionList list = new PossibleActionList(new Board(), mediator, storyGame);
        list.updateRealLists();

        final Position startPosition = new Position(mediator, list);

        return Progressing.getSolution(startPosition, WHITE, level);
    }

    @Test
    public void testTransformete() {
        final Map<Integer, IAnswer> levelExpectedMap = new HashMap<>();
        levelExpectedMap.put(1, new AnswerSimbol(1, 0, 0, 0, 'Q'));
//        levelExpectedMap.put(2,new Answer(7,7,6,6,'Q'));
//        levelExpectedMap.put(3,3.);
//        levelExpectedMap.put(4,1.);

        for (final Integer level : levelExpectedMap.keySet()) {
            final IAnswer expected = levelExpectedMap.get(level);
            final IAnswer actual = transformate(level);
            assertEquals(expected, actual);
        }
    }

    private IAnswer transformate(final int level) {
        final IMediator mediator = new Mediator();
        final StoryGame storyGame = new StoryGame(mediator);

        final Figure figureW = new Pawn(WHITE);
        final IField fieldW = new Field(1, 0);
        mediator.addNewConnection(fieldW, figureW);

        final PossibleActionList list = new PossibleActionList(new Board(), mediator, storyGame);
        list.updateRealLists();

        final Position startPosition = new Position(mediator, list);

        return Progressing.getSolution(startPosition, WHITE, level);
    }

   /* @Test
    public void testMateLevel2() {
        final IMediator mediator = new Mediator();
        final StoryGame storyGame = new StoryGame(mediator);

        final Figure whiteKing = new King(WHITE);
        final IField fieldWhiteKing = new Field(0, 3);
        mediator.addNewConnection(fieldWhiteKing, whiteKing);

        final Figure whiteQueen = new Rook(WHITE);
        final IField fieldQ = new Field(7, 1);
        mediator.addNewConnection(fieldQ, whiteQueen);

        final Figure blackKing = new King(Color.BLACK);
        final IField fieldBlackKing = new Field(0, 0);
        mediator.addNewConnection(fieldBlackKing, blackKing);


        final PossibleActionList list = new PossibleActionList(new Board(), mediator, storyGame);
        list.updateRealLists();

        final Position startPosition = new Position(mediator, list);

        final IAnswer actual = Progressing.getSolution(startPosition, WHITE, 4);

        final IAnswer expected = new AnswerSimbol(0, 3, 1, 2, 'Q');

        ConsoleBoardWriter.printAnswer(actual);

        assertEquals(expected, actual);
    }*/

    @Test
    public void testMateLevel1() {
        final IMediator mediator = new Mediator();
        final StoryGame storyGame = new StoryGame(mediator);

        final Figure whiteKing = new King(WHITE);
        final IField fieldWhiteKing = new Field(2, 2);
        mediator.addNewConnection(fieldWhiteKing, whiteKing);

        final Figure whiteQueen = new Queen(WHITE);
        final IField fieldQ = new Field(7, 1);
        mediator.addNewConnection(fieldQ, whiteQueen);

        final Figure blackKing = new King(Color.BLACK);
        final IField fieldBlackKing = new Field(0, 0);
        mediator.addNewConnection(fieldBlackKing, blackKing);


        final PossibleActionList list = new PossibleActionList(new Board(), mediator, storyGame);
        list.updateRealLists();

        final Position startPosition = new Position(mediator, list);

        Progressing.print(startPosition, 0);

        final IAnswer answer = Progressing.getSolution(startPosition, WHITE, 1);

        ConsoleBoardWriter.printAnswer(answer);
    }

   /* @Test
    public void testMateRookLevel1() {
        final IMediator mediator = new Mediator();
        final StoryGame storyGame = new StoryGame(mediator);

        final Figure whiteKing = new King(WHITE);
        final IField fieldWhiteKing = new Field(1, 2);
        mediator.addNewConnection(fieldWhiteKing, whiteKing);

        final Figure whiteQueen = new Rook(WHITE);
        final IField fieldQ = new Field(7, 1);
        mediator.addNewConnection(fieldQ, whiteQueen);

        final Figure blackKing = new King(Color.BLACK);
        final IField fieldBlackKing = new Field(0, 0);
        mediator.addNewConnection(fieldBlackKing, blackKing);


        final PossibleActionList list = new PossibleActionList(new Board(), mediator, storyGame);
        list.updateRealLists();

        final Position startPosition = new Position(mediator, list);

        final IAnswer answer = Progressing.getSolution(startPosition, WHITE, 1);

        ConsoleBoardWriter.printAnswer(answer);
        final IAnswer expected = new AnswerSimbol(7, 1, 7, 0, 'Q');

        assertEquals(expected, answer);
    }*/

    @Test
    public void testPawn() {
        final IMediator mediator = new Mediator();
        final StoryGame storyGame = new StoryGame(mediator);

        final Figure whiteKing = new King(WHITE);
        final IField fieldWhiteKing = new Field(7, 7);
        mediator.addNewConnection(fieldWhiteKing, whiteKing);

        final Figure whitePawn = new Pawn(WHITE);
        final IField fieldQ = new Field(6, 4);
        mediator.addNewConnection(fieldQ, whitePawn);

        final Figure blackKing = new King(Color.BLACK);
        final IField fieldBlackKing = new Field(0, 0);
        mediator.addNewConnection(fieldBlackKing, blackKing);

        final PossibleActionList list = new PossibleActionList(new Board(), mediator, storyGame);
        list.updateRealLists();

        final Position startPosition = new Position(mediator, list);

        final IAnswer answer = Progressing.getSolution(startPosition, WHITE, 4);

        ConsoleBoardWriter.printAnswer(answer);

    }
}
