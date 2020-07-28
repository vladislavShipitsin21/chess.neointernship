package neointernship.chess.game.gameplay.moveaction.commands.allow;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.figure.factory.Factory;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.communication.message.ChessCodes;

public class TransformationAfterCommand extends AbstractCommand implements IAllowCommand {

    public TransformationAfterCommand(IBoard board, IMediator mediator) {
        super(board, mediator);
    }

    @Override
    public void execute(IAnswer answer) {
        final IField startField = board.getField(answer.getStartX(), answer.getStartY());

        final Figure startFigure = mediator.getFigure(startField);

        final Figure newFigure = new Factory().createFigure(answer.getSimbol(), startFigure.getColor());

        mediator.deleteConnection(startField);

        mediator.addNewConnection(startField, newFigure);
    }

    @Override
    public ChessCodes getChessCode() {
        return ChessCodes.TRANSFORMATION_AFTER;
    }

}
