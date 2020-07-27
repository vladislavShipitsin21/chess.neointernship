package neointernship.chess.story;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.game.story.StoryGame;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestStory {

    @Test
    public void testGet(){
        IMediator mediator = new Mediator();
        IStoryGame storyGame = new StoryGame(mediator);

        Figure king = new King(Color.WHITE);
        IField field = new Field(0,0);

        mediator.addNewConnection(field,king);

        storyGame.update(king);

        IField result = storyGame.getLastField();
        IField expectedField = new Field(0,0);

        assertNotNull(result);
        assertEquals(expectedField,result);
        assertEquals(king,storyGame.getLastFigureMove());
        assertTrue(storyGame.isMove(king));
    }

    @Test
    public void testUpdate(){

    }
}
