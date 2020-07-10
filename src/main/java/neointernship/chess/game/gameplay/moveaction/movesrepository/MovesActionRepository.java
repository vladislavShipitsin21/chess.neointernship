package neointernship.chess.game.gameplay.moveaction.movesrepository;

import neointernship.chess.game.gameplay.moveaction.CheckActionCommand;
import neointernship.chess.game.gameplay.moveaction.IMoveActionCommand;
import neointernship.chess.game.gameplay.moveaction.NormalActionCommand;
import neointernship.chess.game.model.enums.KingState;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;

import java.util.HashMap;

public class MovesActionRepository {
    private final HashMap<KingState, IMoveActionCommand> actionCommandMap;
    private final IMoveActionCommand normalActionCommand;
    private final IMoveActionCommand checkActionCommand;

    private final IMediator mediator;
    private final IPossibleActionList possibleActionList;
    private final IBoard board;

    public MovesActionRepository(final IMediator mediator,
                                 final IPossibleActionList possibleActionList,
                                 final IBoard board) {
        this.mediator = mediator;
        this.possibleActionList = possibleActionList;
        this.board = board;

        normalActionCommand = new NormalActionCommand(mediator, possibleActionList, board);
        checkActionCommand = new CheckActionCommand(mediator, possibleActionList, board);

        actionCommandMap = new HashMap<KingState, IMoveActionCommand>() {
            {
                put(KingState.FREE, normalActionCommand);
                put(KingState.CHECK, checkActionCommand);
            }
        };
    }

    public IMoveActionCommand getCommand(final KingState state) {
        return actionCommandMap.get(state);
    }
}
