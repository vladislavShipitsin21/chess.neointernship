package neointernship.chess.game.gameplay.moveaction.commands.allow;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.factory.Factory;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.communication.message.TurnStatus;

public class TransformationCommand extends AbstractCommand implements IAllowCommand {

    public TransformationCommand(IBoard board, IMediator mediator) {
        super(board, mediator);
    }

    @Override
    public void execute(IAnswer answer) {
        final IField startField = board.getField(answer.getStartX(), answer.getStartY());
        final IField finalField = board.getField(answer.getFinalX(), answer.getFinalY());

        Color color = Color.WHITE;
        if(finalField.getXCoord() == board.getSize() - 1){
            color = Color.BLACK;
        }

        final Figure finalFigure = mediator.getFigure(finalField);

        Figure newFigure = new Factory().createFigure(answer.getSimbol(),color);

        mediator.deleteConnection(startField);
        if(finalFigure != null){
            mediator.deleteConnection(finalField);
        }
        mediator.addNewConnection(finalField,newFigure);
    }

    @Override
    public TurnStatus getChessCode() {
        return TurnStatus.TRANSFORMATION;
    }

}
