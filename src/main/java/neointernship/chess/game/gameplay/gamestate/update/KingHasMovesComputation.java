package neointernship.chess.game.gameplay.gamestate.update;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class KingHasMovesComputation {
    private final IPossibleActionList possibleActionList;
    private final IMediator mediator;

    public KingHasMovesComputation(final IPossibleActionList possibleActionList,
                                     final IMediator mediator) {
        this.possibleActionList = possibleActionList;
        this.mediator = mediator;
    }


    public boolean kingHasMoves(Color color) {
        boolean isKingHasMoves = false;
        King king = mediator.getKing(color);

        Map<Figure, Collection<IField>> figuresMovesMap = possibleActionList.getMap();
        Set<Map.Entry<Figure, Collection<IField>>> entrySet = figuresMovesMap.entrySet();
        for (Map.Entry<Figure, Collection<IField>> pair : entrySet) {
            for (IField field : pair.getValue()) {
                if (Objects.equals(pair.getKey(), king)) {
                    isKingHasMoves = true;
                    break;
                }
            }
        }

        return isKingHasMoves;
    }
}
