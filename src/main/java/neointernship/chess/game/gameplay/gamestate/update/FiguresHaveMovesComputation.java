package neointernship.chess.game.gameplay.gamestate.update;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.Collection;

public class FiguresHaveMovesComputation {
    private final IPossibleActionList possibleActionList;
    private final IMediator mediator;

    public FiguresHaveMovesComputation(final IPossibleActionList possibleActionList,
                                       final IMediator mediator) {
        this.possibleActionList = possibleActionList;
        this.mediator = mediator;
    }

    public boolean check(Color color) {
        for (Figure figure : mediator.getFigures(color)) {
            Collection<IField> collection = possibleActionList.getRealList(figure);
            if (!collection.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
