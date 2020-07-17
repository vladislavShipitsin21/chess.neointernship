package neointernship.chess.game.actions.end;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.GameStateController;
import neointernship.chess.game.gameplay.gamestate.state.GameState;
import neointernship.chess.game.gameplay.init.GameInitializer;
import neointernship.chess.game.gameplay.loop.GameLoop;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.factory.Factory;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.figure.piece.Queen;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.game.story.StoryGame;
import neointernship.chess.logger.GameLogger;
import org.junit.Test;
import org.mockito.Mock;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestMate {

    private void setAnswer(IAnswer answer,int startX,int startY,int finalX,int finalY){
        when(answer.getStartX()).thenReturn(startX);
        when(answer.getStartY()).thenReturn(startY);
        when(answer.getFinalX()).thenReturn(finalX);
        when(answer.getFinalY()).thenReturn(finalY);
    }

    @Test
    public void test(){

        IPlayer player1 = mock(IPlayer.class);
        IPlayer player2 = mock(IPlayer.class);
        IAnswer answer1 = mock(IAnswer.class);

        setAnswer(answer1,6,4,4,4);

        IBoard board = new Board();
        IMediator mediator = new Mediator();

        Figure kingB = new King(Color.BLACK);
        Figure kingW = new King(Color.WHITE);
        Figure queenW = new Queen(Color.WHITE);

        IField fieldKingB = board.getField(0,0);
        IField fieldKingW = board.getField(2,2);
        IField fieldQueenW = board.getField(1,1);

        mediator.addNewConnection(fieldKingB,kingB);
        mediator.addNewConnection(fieldKingW,kingW);
        mediator.addNewConnection(fieldQueenW,queenW);

        IStoryGame storyGame = new StoryGame(mediator);
        IPossibleActionList possibleActionList = new PossibleActionList(board,mediator,storyGame);
        possibleActionList.updateRealLists();
        /*GameLoop gameLoop = new GameLoop(
                mediator,
                possibleActionList,
                board,
                player1,
                player2,
                new GameLogger(100),
                storyGame);

        gameLoop.activate();*/

        GameStateController gameStateController = new GameStateController(
                possibleActionList,
                mediator,
                new GameLogger(100));

        if(!gameStateController.isMatchAlive()){
            gameStateController.showResults();
        }

    }
}
