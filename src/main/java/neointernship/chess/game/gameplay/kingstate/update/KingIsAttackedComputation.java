package neointernship.chess.game.gameplay.kingstate.update;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class KingIsAttackedComputation {
    private final IPossibleActionList possibleActionList;
    private final IMediator mediator;

    public KingIsAttackedComputation(final IPossibleActionList possibleActionList,
                                     final IMediator mediator) {
        this.possibleActionList = possibleActionList;
        this.mediator = mediator;
    }

    public boolean kingIsAttacked(Color color) {
        boolean isKingAttacked = false;
        Figure king = mediator.getKing(color);

        Map<Figure, Collection<IField>> figuresMovesMap = possibleActionList.getMap();
        Set<Map.Entry<Figure, Collection<IField>>> entrySet = figuresMovesMap.entrySet();
        for (Map.Entry<Figure, Collection<IField>> pair : entrySet) {
            for (IField field : pair.getValue()) {
                if (Objects.equals(king, mediator.getFigure(field))) {
                    isKingAttacked = true;
                    break;
                }
            }
        }

        return isKingAttacked;
    }

}
