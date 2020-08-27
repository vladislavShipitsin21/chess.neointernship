package neointernship.bots.straregy;

import neointernship.bots.ChessStage;
import neointernship.bots.functionsH.bonus.debut.FunctionsDebut;
import neointernship.bots.functionsH.IFunctionsH;
import neointernship.bots.functionsH.bonus.endgame.FunctonsEndgame;
import neointernship.bots.functionsH.bonus.midlegame.FunctionsMidlegame;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.*;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.story.IStoryGame;
import neointernship.tree.BuilderTreeWithBonus;
import neointernship.tree.HelperBuilderTree;
import neointernship.tree.INode;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static neointernship.bots.ChessStage.*;

public class StrategyMixed extends Strategy {

    private int countAnswer;
    private final BuilderTreeWithBonus tree;

    private final IFunctionsH functionsDebut;
    private final IFunctionsH functionsMidlegame;
    private final IFunctionsH functionsEndgame;

    private IFunctionsH actualFunctions;

    private ChessStage actualStage;

    private INode tempRoot;

    public StrategyMixed(final Color color) {
        super(color);
        this.countAnswer = 0;
        tree = new BuilderTreeWithBonus(color);

        functionsDebut = new FunctionsDebut();
        functionsMidlegame = new FunctionsMidlegame();
        functionsEndgame = new FunctonsEndgame();

        actualStage = DEBUT;
    }

    @Override
    public IAnswer getAnswer(final Position startPosition) {
        countAnswer++;
        // определение стадии игры

        actualFunctions = functionsDebut;
        updateChessStage(startPosition);

        switch (actualStage){
            case DEBUT: {
                actualFunctions = functionsDebut;
                break;
            }
            case MIDDLEGAME:{
                actualFunctions = functionsMidlegame;
                break;
            }
            case ENDGAME:{
                actualFunctions = functionsEndgame;
                break;
            }
        }
        System.out.println("stage : " + actualStage);

        final INode root = tree.getTree(startPosition,actualFunctions);
        tempRoot = root;

        final IAnswer answer = HelperBuilderTree.getAnswer(root);

        return answer;
    }

    private void updateChessStage(final Position startPosition){
        if(actualStage == ENDGAME) return;;
        if(actualStage == DEBUT){
            if(isMidlegame(startPosition)) {
                actualStage = MIDDLEGAME;
            }
            return;
        }
        if(actualStage == MIDDLEGAME){
           if(isEndgame(startPosition)){
               actualStage = ENDGAME;
           }
        }
    }

    // миттельшпиль начинается после рокировки и развития легких фигур
    private boolean isMidlegame(final Position startPosition){
        if(countAnswer <= 5) return false;
        if(countAnswer > 12) return true;

        final IMediator mediator = startPosition.getMediator();
        final PossibleActionList possibleActionList = startPosition.getPossibleActionList();
        final IStoryGame storyGame = possibleActionList.getStoryGame();

        final Collection<Figure> figures = mediator.getFigures(getColor());

        final Set<Class> classSet = new HashSet<>();
        classSet.add(King.class);
        classSet.add(Bishop.class);
        classSet.add(Knight.class);

        int countMove = 0;
        for(final Figure figure : figures){
            if(classSet.contains(figure.getClass())){
                countMove++;
            }
        }
        if(countMove < 4) return false;

        final Figure king = mediator.getKing(getColor());
        if(!storyGame.isMove(king)) return false;

        return true;
    }
    // считаем сколько осталось фигур ( не пешек)
    // эндшпиль только без ферзей
    private boolean isEndgame(final Position startPosition){

        int countFigure = 0;
        final IMediator mediator = startPosition.getMediator();
        final Collection<Figure> figures = mediator.getFigures();
        for(final Figure figure : figures){
            if(figure.getClass() != Pawn.class){
                if(figure.getClass() == Queen.class) return false;
                countFigure++;
            }
        }
        return countFigure <= 6;
    }
}
