package neointernship.chess.game.gameplay.moveaction;

import neointernship.chess.game.gameplay.kingstate.update.KingIsAttackedComputation;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.MoveState;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MoveCorrectnessValidator {
    private final IMediator mediator;
    private final IPossibleActionList possibleActionList;
    private final IBoard board;
    private final KingIsAttackedComputation kingIsAttackedComputation;

    public MoveCorrectnessValidator(final IMediator mediator,
                                    IPossibleActionList possibleActionList,
                                    final IBoard board) {
        this.mediator = mediator;
        this.possibleActionList = possibleActionList;
        this.board = board;
        this.kingIsAttackedComputation = new KingIsAttackedComputation(possibleActionList, mediator);
    }

    public MoveState check(final Color color, final IAnswer answer) {
        MoveState moveState = MoveState.NOT_ALLOWED_MOVE_VALID;
        IField startField = board.getField(answer.getStartX(), answer.getStartY());
        IField finalField = board.getField(answer.getFinalX(), answer.getFinalY());

        Figure figure = mediator.getFigure(startField);

        if (figure == null || figure.getColor() != color) {
            return moveState;
        }

        Set<Map.Entry<Figure, Collection<IField>>> entrySet = possibleActionList.getMap().entrySet();
        for (Map.Entry<Figure, Collection<IField>> pair : entrySet) {
            if (pair.getKey() == figure) {
                for (IField field : pair.getValue()) {
                    if (Objects.equals(field, finalField)) {
                        moveState = MoveState.ALLOWED;
                        break;
                    }
                }
            }
        }

        return moveState;
    }
}
