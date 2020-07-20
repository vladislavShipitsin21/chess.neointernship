package neointernship.chess.game.gameplay.figureactions.patterns.real;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.kingstate.update.KingIsAttackedComputation;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.game.story.StoryGame;

import java.util.ArrayList;
import java.util.Collection;

public class RealBasicPatterns implements IRealBasicPatterns {
    private final IMediator mediator;
    private final IBoard board;
    private final IPossibleActionList possibleActionList;
    private KingIsAttackedComputation kingIsAttackedComputation;
    private final IStoryGame storyGame;

    public RealBasicPatterns(final IMediator mediator,
                             final IPossibleActionList possibleActionList,
                             final IBoard board,
                             final IStoryGame storyGame) {
        this.mediator = mediator;
        this.board = board;
        this.possibleActionList = possibleActionList;
        this.storyGame = storyGame;
    }

    @Override
    public Collection<IField> getRealMoveList(Figure figure, Collection<IField> potentialMoveList) {

        ArrayList<IField> realList = new ArrayList<>();
        IField startField = mediator.getField(figure);

        for (IField finalField : potentialMoveList) {

            IMediator newMediator = new Mediator(mediator);
            IStoryGame newStoryGame = new StoryGame((StoryGame) storyGame);
            IPossibleActionList newPossibleActionList = new PossibleActionList(board, newMediator, newStoryGame);

            // если это рокировка то необходимо проверить 3 клетки
            if (figure.getClass() == King.class &&
                    Math.abs(startField.getYCoord() - finalField.getYCoord()) == 2) {

                Collection<IField> forCastling = new ArrayList<>();

                int dif = startField.getYCoord() < finalField.getYCoord() ? 1 : -1;

                forCastling.add(startField);
                forCastling.add(board.getField(startField.getXCoord(), startField.getYCoord() + dif));
                forCastling.add(finalField);

                boolean isAttack = false;
                kingIsAttackedComputation = new KingIsAttackedComputation(possibleActionList, mediator);
                for(IField tempField : forCastling){
                    isAttack |= kingIsAttackedComputation.fieldIsAttacked(tempField,figure.getColor());
                }
                if(!isAttack) realList.add(finalField);
            } else {


                Figure finalFigure = newMediator.getFigure(finalField);

                newMediator.deleteConnection(startField);
                if (finalFigure != null) {
                    newMediator.deleteConnection(finalField);
                }
                newMediator.addNewConnection(finalField, figure);
                newStoryGame.update(mediator.getFigure(startField));
                newPossibleActionList.updatePotentialLists();

                kingIsAttackedComputation = new KingIsAttackedComputation(newPossibleActionList, newMediator);

                if (!kingIsAttackedComputation.kingIsAttacked(figure.getColor())) {
                    realList.add(finalField);
                }
            }
        }
        return realList;
    }
}
