package neointernship.chess.game.model.player;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.tree.BuilderTreeThread;
import neointernship.tree.HelperBuilderTree;
import neointernship.tree.INode;

public class MiniMaxThreadBot extends BotTree {

    public MiniMaxThreadBot(final Color color,final int maxDepth) {
        super("MiniMaxThreadBot", color, maxDepth);
        builderTree = new BuilderTreeThread(maxDepth,getColor());
    }
}
