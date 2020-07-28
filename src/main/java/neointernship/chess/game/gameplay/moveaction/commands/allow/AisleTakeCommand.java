package neointernship.chess.game.gameplay.moveaction.commands.allow;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.communication.message.TurnStatus;

/**
 * Класс реализующий команду взятие на проходе
 */
public class AisleTakeCommand extends AbstractCommand implements IAllowCommand {

    public AisleTakeCommand(IBoard board, IMediator mediator) {
        super(board, mediator);
    }

    @Override
    public void execute(IAnswer answer) {
        final IField startField = board.getField(answer.getStartX(), answer.getStartY());
        final IField finalField = board.getField(answer.getFinalX(), answer.getFinalY());

        final IField fieldAttackPawn = board.getField(answer.getStartX(),answer.getFinalY());

        final Figure startFigure = mediator.getFigure(startField);

        mediator.deleteConnection(startField);
        mediator.addNewConnection(finalField,startFigure);
        mediator.deleteConnection(fieldAttackPawn);

    }

    @Override
    public TurnStatus getTurnStatus() {
        return TurnStatus.AISLE_TAKE;
    }

}
