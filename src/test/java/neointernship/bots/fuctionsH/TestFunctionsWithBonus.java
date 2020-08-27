package neointernship.bots.fuctionsH;

import javafx.geometry.Pos;
import neointernship.bots.TestInitGameMap;
import neointernship.bots.functionsH.bonus.debut.FunctionsDebut;
import neointernship.chess.game.console.ConsoleBoardWriter;
import neointernship.chess.game.end.TestHeadEnd;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.player.Bot;
import neointernship.chess.game.model.player.BotMixidStrategy;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.game.story.StoryGame;
import neointernship.tree.BuilderTreeWithBonus;
import neointernship.tree.HelperBuilderTree;
import neointernship.tree.INode;
import org.junit.Test;

import java.util.Collection;

public class TestFunctionsWithBonus {

    @Test
    public void test(){
        final IMediator mediator = new Mediator();


        final IStoryGame storyGame = new StoryGame(mediator);
        final IPossibleActionList possibleActionList = new PossibleActionList(new Board(),mediator,storyGame);
        possibleActionList.updateRealLists();

        Bot mixid = new BotMixidStrategy(Color.WHITE);
        IAnswer answer = mixid.getAnswer(mediator,possibleActionList);

        BuilderTreeWithBonus tree = new BuilderTreeWithBonus(Color.WHITE);

        final Position position = new Position(mediator,possibleActionList);
        final INode root = tree.getTree(position,new FunctionsDebut());

        Collection<INode> nodes = HelperBuilderTree.getAllChildren(root);

        for(INode node : nodes){
            ConsoleBoardWriter printer = new ConsoleBoardWriter(node.getCore().getMediator(),new Board());
            printer.printPosition(node.getCore());
        }

        System.out.println(nodes.size());

        ConsoleBoardWriter.printAnswer(answer);
    }

}
