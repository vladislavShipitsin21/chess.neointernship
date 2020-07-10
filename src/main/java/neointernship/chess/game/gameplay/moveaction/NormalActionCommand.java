package neointernship.chess.game.gameplay.moveaction;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;

/**
 * Реализация хода в нормальной ситуации
 */
public class NormalActionCommand implements IMoveActionCommand {

    private final IMediator mediator;
    private final IPossibleActionList possibleActionList;
    private final IBoard board;

    public NormalActionCommand(final IMediator mediator,
                              final IPossibleActionList possibleActionList,
                              final IBoard board) {
        this.mediator = mediator;
        this.possibleActionList = possibleActionList;
        this.board = board;
    }


    @Override
    public boolean execute(Color color, IAnswer answer) {
        return false;
    }
}
