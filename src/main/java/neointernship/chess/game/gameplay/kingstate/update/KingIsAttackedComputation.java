package neointernship.chess.game.gameplay.kingstate.update;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.Collection;

public final class KingIsAttackedComputation {
    private final IPossibleActionList possibleActionList;
    private final IMediator mediator;

    public KingIsAttackedComputation(final IPossibleActionList possibleActionList,
                                     final IMediator mediator) {
        this.possibleActionList = possibleActionList;
        this.mediator = mediator;
    }

    public boolean kingIsAttacked(final Color color) {
        final Figure king = mediator.getKing(color);
        final IField kingField = mediator.getField(king);

        final Color opponentColor = Color.swapColor(color);
        for (final Figure figure : mediator.getFigures(opponentColor)) {
            final Collection<IField> collection = possibleActionList.getPotentialList(figure);
            if (collection.contains(kingField)) {
                return true;
            }
        }
        return false;
    }

    public boolean fieldIsAttacked(final IField field, final Color color) {
        final Color opponentColor = Color.swapColor(color);
        for (final Figure figure : mediator.getFigures(opponentColor)) {
            final Collection<IField> collection = possibleActionList.getPotentialList(figure);
            if (collection.contains(field)) {
                return true;
            }
        }
        return false;
    }
}
