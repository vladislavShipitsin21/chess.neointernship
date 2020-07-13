package neointernship.chess.game.gameplay.gamestate.update;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.figure.piece.Figure;
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
        Figure king = mediator.getKing(color);

        Set<Map.Entry<Figure, Collection<IField>>> entrySet = possibleActionList.getMap().entrySet();
        for (Map.Entry<Figure, Collection<IField>> pair : entrySet) {
            if (Objects.equals(pair.getKey(), king)) {
                if (pair.getValue().size() != 0 ) {
                    System.out.println("WTF: " + pair.getValue().size());
                    isKingHasMoves = true;
                    break;
                }
            }
        }

        return isKingHasMoves;
    }
}
