package neointernship.bots.fuctionsH;

import neointernship.bots.functionsH.TargetFunction;
import neointernship.chess.game.console.ConsoleBoardWriter;
import neointernship.chess.game.console.IConsoleBoardWriter;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.story.StoryGame;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static neointernship.bots.InitGameMap.initGameMap;

public class TestFunctionsH {

    @Test
    public void testTarget() {
        final IMediator mediator = initGameMap();
        final StoryGame storyGame = new StoryGame(mediator);
        final Board board = new Board();

        final IConsoleBoardWriter printer = new ConsoleBoardWriter(mediator, board);

        final PossibleActionList list = new PossibleActionList(board, mediator, storyGame);
        list.updateRealLists();

        final Position start = new Position(mediator, list);

        assertEquals(0., TargetFunction.price(start, Color.WHITE, Color.WHITE));

        mediator.deleteConnection(board.getField(0, 0));

        final Position finish = new Position(mediator, list);

        assertEquals(5. / 38, TargetFunction.price(finish, Color.WHITE, Color.WHITE));

        final Position finishBlack = new Position(mediator, list);

        assertEquals(-5. / 38, TargetFunction.price(finishBlack, Color.BLACK, Color.WHITE));
    }


}
