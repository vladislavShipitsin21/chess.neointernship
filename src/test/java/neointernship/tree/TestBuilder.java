package neointernship.tree;

import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.answer.AnswerSimbol;
import neointernship.chess.game.model.answer.IAnswer;
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

import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static neointernship.chess.game.model.enums.Color.WHITE;

public class TestBuilder {

    @Test
    public void testLevel1() {
        final IMediator mediator = new Mediator();
        final StoryGame storyGame = new StoryGame(mediator);

        final Figure whiteKing = new King(WHITE);
        final IField fieldWhiteKing = new Field(0, 2);
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

        final BuilderTree builderTree = new BuilderTree(1, WHITE);

        final INode root = builderTree.getTree(startPosition);

        final Position result = root.getEdges().stream().map(e -> e.getChild().getCore()).max(Position::compareTo).get();

        System.out.println(result.getPrice());
        System.out.println(root.getEdges().size());
    }

    @Test
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

        final BuilderTree builderTree = new BuilderTree(4, WHITE);

        final INode root = builderTree.getTree(startPosition);

        final IAnswer actual = HelperBuilderTree.getAnswer(root);

        final IAnswer expected = new AnswerSimbol(0, 3, 1, 2, 'Q');

        assertEquals(expected, actual);
    }

    /*@Test
    public void testAB() {
        final IMediator mediator = new Mediator();
        final StoryGame storyGame = new StoryGame(mediator);

        final Figure whiteKing = new King(WHITE);
        final IField fieldWhiteKing = new Field(7, 7);
        mediator.addNewConnection(fieldWhiteKing, whiteKing);
        storyGame.update(whiteKing);

        final Figure whiteRook = new Rook(WHITE);
        final IField fieldRook = new Field(3, 6);
        mediator.addNewConnection(fieldRook, whiteRook);

        final Figure blackKing = new King(Color.BLACK);
        final IField fieldBlackKing = new Field(0, 0);
        mediator.addNewConnection(fieldBlackKing, blackKing);

        final Figure blackPawn = new Pawn(Color.BLACK);
        final IField fieldBlackPawn = new Field(4, 6);
        mediator.addNewConnection(fieldBlackPawn, blackPawn);

        final PossibleActionList list = new PossibleActionList(new Board(), mediator, storyGame);
        list.updateRealLists();

        final Position startPosition = new Position(mediator, list);

        final BuilderTree builderTree = new BuilderTree(2, WHITE);

        final INode root = builderTree.getTree(startPosition);

        final IAnswer actual = HelperBuilderTree.getAnswer(root);

        final IAnswer expected = new AnswerSimbol(3, 6, 4, 6, 'Q');

        assertEquals(expected, actual);

        final Collection<INode> collections = HelperBuilderTree.getAllChildren(root);
        System.out.println(collections.size());
        assertTrue(collections.size() < 42);
    }*/
}
