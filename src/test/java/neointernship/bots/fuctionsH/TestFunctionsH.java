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

   /* @Test
    public void testTarget(){
        IMediator mediator = initGameMap();
        StoryGame storyGame = new StoryGame(mediator);
        Board board = new Board();

        IConsoleBoardWriter printer = new ConsoleBoardWriter(mediator,board);

        PossibleActionList list = new PossibleActionList(board,mediator,storyGame);
        list.updateRealLists();

        Position start = new Position(mediator,list);

        assertEquals(0.,TargetFunction.price(start,Color.WHITE,Color.WHITE));

        mediator.deleteConnection(board.getField(0,0));

        Position finish = new Position(mediator,list);

        assertEquals(5.,TargetFunction.price(finish,Color.WHITE,Color.WHITE));

        Position finishBlack = new Position(mediator,list);

        assertEquals(-5.,TargetFunction.price(finishBlack,Color.BLACK,Color.WHITE));
    }
*/

}
