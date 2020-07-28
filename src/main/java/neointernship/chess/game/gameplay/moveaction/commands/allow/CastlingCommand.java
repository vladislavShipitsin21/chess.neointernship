package neointernship.chess.game.gameplay.moveaction.commands.allow;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.communication.message.TurnStatus;

/**
 * Класс реализующий команду рокировки
 */
public class CastlingCommand extends AbstractCommand implements IAllowCommand {

    public CastlingCommand(IBoard board, IMediator mediator) {
        super(board, mediator);
    }

    @Override
    public void execute(IAnswer answer) {
        IField startFieldKing = board.getField(answer.getStartX(),answer.getStartY());
        IField finalFieldKing = board.getField(answer.getFinalX(),answer.getFinalY());

        Figure king = mediator.getFigure(startFieldKing);

        int yStartCoordRook = 0; // todo корректно только для класических шахмат !!!
        int difCoordRook = 1;

        // если двигаемся вправо
        if(startFieldKing.getYCoord() < finalFieldKing.getYCoord()){
            yStartCoordRook = 7;
            difCoordRook = -1;
        }
        IField startFieldRook = board.getField(startFieldKing.getXCoord(),yStartCoordRook);

        IField finalFieldRook = board.getField(
                finalFieldKing.getXCoord(),
                finalFieldKing.getYCoord() + difCoordRook);

        Figure rook = mediator.getFigure(startFieldRook);

        mediator.deleteConnection(startFieldKing);
        mediator.deleteConnection(startFieldRook);
        mediator.addNewConnection(finalFieldKing,king);
        mediator.addNewConnection(finalFieldRook,rook);
    }

    @Override
    public TurnStatus getTurnStatus() {
        return TurnStatus.CASTLING;
    }

}
