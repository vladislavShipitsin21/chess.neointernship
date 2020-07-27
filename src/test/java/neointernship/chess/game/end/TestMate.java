package neointernship.chess.game.end;

import neointernship.chess.game.gameplay.activecolorcontroller.ActiveColorController;
import neointernship.chess.game.gameplay.activecolorcontroller.IActiveColorController;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.loop.GameLoop;
import neointernship.chess.game.gameplay.loop.IGameLoop;
import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.figure.piece.Queen;
import neointernship.chess.game.model.figure.piece.Rook;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.game.story.StoryGame;
import neointernship.web.client.communication.message.TurnStatus;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestMate {

    @Test
    public void testLinerMate(){
        IBoard board = new Board();
        IMediator mediator = new Mediator();
        IStoryGame storyGame = new StoryGame(mediator);

        IPossibleActionList possibleActionList = new PossibleActionList(board,mediator,storyGame);
        IActiveColorController activeColorController = new ActiveColorController();

        final Figure kingW = new King(Color.BLACK);
        final IField fieldKingW = new Field(7,4);

        final Figure kingB = new King(Color.WHITE);
        final IField fieldKingB = new Field(0,4);

        final Figure rook1 = new Rook(Color.WHITE);
        final IField fieldRook1 = new Field(6,7);

        final Figure rook2 = new Rook(Color.WHITE);
        final IField fieldRook2 = new Field(6,0);

        mediator.addNewConnection(fieldKingW,kingW);
        mediator.addNewConnection(fieldKingB,kingB);
        mediator.addNewConnection(fieldRook1,rook1);
        mediator.addNewConnection(fieldRook2,rook2);

        IAnswer answer = new Answer(6,0,7,0,'Q');

        IGameLoop gameLoop = new GameLoop(mediator,possibleActionList,board,activeColorController,storyGame);

        possibleActionList.updateRealLists();

        assertTrue(gameLoop.isAlive());

        TurnStatus result = gameLoop.doIteration(answer);

        assertEquals(result, TurnStatus.MOVE);

        System.out.println(gameLoop.getMatchResult().getValue());
        assertFalse(gameLoop.isAlive());
    }

    @Test
    public void testQueenMate() {
        IBoard board = new Board();
        IMediator mediator = new Mediator();
        IStoryGame storyGame = new StoryGame(mediator);

        IPossibleActionList possibleActionList = new PossibleActionList(board, mediator, storyGame);
        IActiveColorController activeColorController = new ActiveColorController();

        final Figure kingW = new King(Color.BLACK);
        final IField fieldKingW = new Field(7, 4);

        final Figure kingB = new King(Color.WHITE);
        final IField fieldKingB = new Field(5, 4);

        final Figure queen = new Queen(Color.WHITE);
        final IField fieldQ = new Field(6, 0);

        mediator.addNewConnection(fieldKingW, kingW);
        mediator.addNewConnection(fieldKingB, kingB);
        mediator.addNewConnection(fieldQ, queen);


        IAnswer answer = new Answer(6, 0, 6, 4, 'Q');

        IGameLoop gameLoop = new GameLoop(mediator, possibleActionList, board, activeColorController, storyGame);

        possibleActionList.updateRealLists();

        assertTrue(gameLoop.isAlive());

        assertEquals(Color.WHITE, activeColorController.getCurrentColor());

        TurnStatus result = gameLoop.doIteration(answer);

        assertEquals(Color.BLACK, activeColorController.getCurrentColor());

        assertEquals(result, TurnStatus.MOVE);

        System.out.println(gameLoop.getMatchResult().getValue());
        assertFalse(gameLoop.isAlive());
        assertEquals(EnumGameState.MATE, gameLoop.getMatchResult().getValue());

        assertEquals(queen,storyGame.getLastFigureMove());
    }

}
