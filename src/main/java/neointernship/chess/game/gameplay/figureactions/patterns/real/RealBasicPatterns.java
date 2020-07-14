package neointernship.chess.game.gameplay.figureactions.patterns.real;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.kingstate.update.KingIsAttackedComputation;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.ArrayList;
import java.util.Collection;

public class RealBasicPatterns implements IRealBasicPatterns {
    private final IMediator mediator;
    private final IBoard board;
    private final IPossibleActionList possibleActionList;
    private KingIsAttackedComputation kingIsAttackedComputation;

    public RealBasicPatterns(IMediator mediator, IPossibleActionList possibleActionList, final IBoard board) {
        this.mediator = mediator;
        this.board = board;
        this.possibleActionList = possibleActionList;
    }


    @Override
    public Collection<IField> getRealMoveList(Figure figure, Collection<IField> potentialMoveList) {
        ArrayList<IField> realList = new ArrayList<>();
        IField startField = mediator.getField(figure);

        for (IField finalField : potentialMoveList) {

            IMediator newMediator = new Mediator(mediator);
            IPossibleActionList newPossibleActionList = new PossibleActionList(board, newMediator);

            Figure finalFigure = newMediator.getFigure(finalField);

            newMediator.deleteConnection(startField);
            if (finalFigure != null) {
                newMediator.deleteConnection(finalField);
            }
            newMediator.addNewConnection(finalField, figure);
            newPossibleActionList.updatePotentialLists();

            kingIsAttackedComputation = new KingIsAttackedComputation(newPossibleActionList, newMediator);

            if (!kingIsAttackedComputation.kingIsAttacked(figure.getColor())) {
                realList.add(finalField);
            }
        }

        return realList;
    }
}
