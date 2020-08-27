package neointernship.chess.game.model.player;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.*;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.tree.BuilderTree;
import neointernship.tree.HelperBuilderTree;
import neointernship.tree.IBuilderTree;
import neointernship.tree.INode;

import java.util.HashMap;

public abstract class BotTree extends Bot {

    private final static int MAX_COUNT_NODE = 1000;

    protected IBuilderTree builderTree;
    final protected int maxDepth;
//    private HashMap<Class,Integer> classMap;

    public BotTree(final String name, final Color color, final int maxDepth) {
        super(name,color);
        this.maxDepth = maxDepth;
//        classMap = new HashMap<>();
        //initMap();
    }
    /*private void initMap(){
        classMap.put(Queen.class,27);
        classMap.put(Rook.class,14);
        classMap.put(Bishop.class,13);
        classMap.put(Knight.class,8);
        classMap.put(King.class,8);
        classMap.put(Pawn.class,4);
    }*/

   /* private int getMaxDepth(final IMediator mediator){
        int mark1 = 0;
        int mark2 = 0;
        int allMark = 1;

        for(final Figure figure : mediator.getFigures()){
            if(figure.getColor() == getColor()) {
                mark1 += classMap.get(figure.getClass());
            }else{
                mark2 += classMap.get(figure.getClass());
            }
        }
        allMark *= mark1;
        allMark *= mark2;

        if(allMark > MAX_COUNT_NODE) {
            System.out.println("allMark : " + allMark);
            return 2;
        }

        allMark *= mark1;

        if(allMark > MAX_COUNT_NODE) {
            System.out.println("allMark : " + allMark);
            return 3;
        }

        allMark *= mark2;

        if(allMark > MAX_COUNT_NODE) {
            System.out.println("allMark : " + allMark);
            return 4;
        }
        return 1;
    }*/

    @Override
    public IAnswer getAnswer(final IMediator mediator, final IPossibleActionList possibleActionList) {
        time.start();
        // определить глубину
       // maxDepth = getMaxDepth(mediator);
        System.out.println(getName() + " depth : " + maxDepth);

        final Position startPosition = new Position(mediator, possibleActionList);

        final BuilderTree builderTree = new BuilderTree(maxDepth, getColor());
        final INode root = builderTree.getTree(startPosition);

        final IAnswer answer = HelperBuilderTree.getAnswer(root);

        time.finish();
        System.out.println(time.getCountAddress() + ") " + getName() + " - " + time.getTime());

        return answer;
    }
}
