package neointernship.chess.game.moveactions;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.moveaction.MoveCorrectnessValidator;
import neointernship.chess.game.gameplay.moveaction.commands.allow.AllowMoveCommand;
import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.game.story.StoryGame;
import neointernship.web.client.communication.message.ChessCodes;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMove extends TestMoveCorrectValidator {


   /* private static IBoard board;
    private static IMediator mediator;
    private static IStoryGame storyGame;
    private static IPossibleActionList possibleActionList;
    private static MoveCorrectnessValidator validator;*/
    private final static AllowMoveCommand allowMoveCommand = null;

    @BeforeClass
    public static void init(){
        AllowMoveCommand allowMoveCommand = new AllowMoveCommand(mediator,possibleActionList,board,storyGame);
    }
    @Test
    public void testMove(){

        Figure figure = new King(Color.WHITE);
        IField field = new Field(0,0);

        addFigure(field,figure);

        IAnswer answer = new Answer(0,0,1,1,'Q');

        ChessCodes result = allowMoveCommand.execute(figure.getColor(),answer);

        assertEquals(ChessCodes.MOVE,result);

    }
}
