package neointernship.chess.game.gameplay.figureactions.patterns.real;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.kingstate.update.KingIsAttackedComputation;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.ArrayList;
import java.util.Collection;

public class RealBasicPatterns implements IRealBasicPatterns {
    private final IMediator mediator;
    private final IPossibleActionList possibleActionList;
    private final KingIsAttackedComputation kingIsAttackedComputation;

    public RealBasicPatterns(IMediator mediator, IPossibleActionList possibleActionList) {
        this.mediator = mediator;
        this.possibleActionList = possibleActionList;
        kingIsAttackedComputation = new KingIsAttackedComputation(possibleActionList, mediator);
    }


    @Override
    public Collection<IField> getRealMoveList(Figure figure, Collection<IField> potentialMoveList) {
        ArrayList<IField> realList = new ArrayList<>();
        IField startField = mediator.getField(figure);

        for (IField finalField : potentialMoveList) {
            IMediator newMediator = new Mediator(mediator);
            Figure finalFigure = newMediator.getFigure(finalField);

            newMediator.deleteConnection(startField);
            if (finalFigure != null) {
                newMediator.deleteConnection(finalField);
            }
            newMediator.addNewConnection(finalField, figure);

            if (!kingIsAttackedComputation.kingIsAttacked(figure.getColor())) {
                System.out.println();
                realList.add(finalField);
            }
        }

        return realList;
    }
}
