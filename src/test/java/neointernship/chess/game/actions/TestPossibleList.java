package neointernship.chess.game.actions;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.Pawn;
import neointernship.chess.game.model.figure.piece.Queen;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.game.story.StoryGame;
import org.junit.Test;

import static neointernship.chess.game.model.enums.Color.WHITE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestPossibleList {
    @Test
    public void testEquals() {
        final IBoard board = new Board();
        final IMediator mediator = new Mediator();
        mediator.addNewConnection(board.getField(0, 0), new Queen(Color.BLACK));

        final IStoryGame storyGame = new StoryGame(mediator);

        final IPossibleActionList possibleActionList =
                new PossibleActionList(board, mediator, storyGame);

        final Mediator mediatorCopy = new Mediator(mediator);

        final PossibleActionList possibleActionListCopy =
                new PossibleActionList(board, mediatorCopy, new StoryGame(mediatorCopy));

        assertEquals(possibleActionList, possibleActionListCopy);

        mediatorCopy.deleteConnection(board.getField(0, 0));

        possibleActionListCopy.updateRealLists();
        possibleActionList.updateRealLists();

        assertEquals(0, mediatorCopy.getFigures().size());
        assertNotEquals(possibleActionList, possibleActionListCopy);

    }

    @Test
    public void testTransformatePawn() {
        final IMediator mediator = new Mediator();
        final StoryGame storyGame = new StoryGame(mediator);

        final Figure figureW = new Pawn(WHITE);
        final IField fieldW = new Field(6, 0);
        mediator.addNewConnection(fieldW, figureW);

        final PossibleActionList list = new PossibleActionList(new Board(), mediator, storyGame);
        list.updateRealLists();


    }
}
