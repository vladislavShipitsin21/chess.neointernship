package neointernship.chess.game.gameplay.moveaction;

import neointernship.chess.game.gameplay.kingstate.update.KingIsAttackedComputation;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;

/**
 * Проверка хода в ситуации шаха
 */
public class CheckActionCommand implements IMoveActionCommand {

    private final IMediator mediator;
    private final IPossibleActionList possibleActionList;
    private final IBoard board;

    public CheckActionCommand(final IMediator mediator,
                              final IPossibleActionList possibleActionList,
                              final IBoard board) {
        this.mediator = mediator;
        this.possibleActionList = possibleActionList;
        this.board = board;
    }

    @Override
    public boolean execute(final Color color,final IAnswer answer) {

        IField fieldOut = answer.getOut();
        IField fieldIn = answer.getIn();
        Figure figure = mediator.getFigure(fieldOut);

        mediator.deleteConnection(fieldIn);
        mediator.addNewConnection(fieldOut,figure);

        KingIsAttackedComputation kic = new KingIsAttackedComputation(possibleActionList,mediator);

        if(kic.kingIsAttacked(color)){
            // если шах остался то откатываем
        }else{
            // ход допускаем
        }
        return false;
    }
}
