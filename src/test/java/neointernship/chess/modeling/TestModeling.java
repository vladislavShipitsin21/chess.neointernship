package neointernship.chess.modeling;

import neointernship.bots.modeling.Modeling;
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

import static org.junit.Assert.assertEquals;

public class TestModeling {
    @Test
    public void testStartPosition(){
        IMediator mediator = new Mediator();
        StoryGame storyGame = new StoryGame(mediator);
        Figure figure = new King(Color.WHITE);
        IField field = new Field(0,0);

        mediator.addNewConnection(field,figure);

        PossibleActionList list = new PossibleActionList(new Board(),mediator,storyGame);
        list.updateRealLists();

        Position startPosition = new Position(mediator,list);

        Collection<Position> resultPositions = Modeling.modeling(startPosition,storyGame,figure.getColor());

        assertEquals(3,resultPositions.size());
    }
}
