package neointernship.chess.game.gameplay.moveaction.movesrepository;

import neointernship.chess.game.gameplay.moveaction.commands.AllowedMoveCommand;
import neointernship.chess.game.gameplay.moveaction.commands.CheckMoveCommand;
import neointernship.chess.game.gameplay.moveaction.commands.IMoveCommand;
import neointernship.chess.game.gameplay.moveaction.commands.MoveNotValidMoveCommand;
import neointernship.chess.game.model.enums.MoveState;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;

import java.util.HashMap;

public class MovesRepository {
    private final HashMap<MoveState, IMoveCommand> actionCommandMap;
    private final IMoveCommand allowedMoveCommand;
    private final IMoveCommand checkMoveCommand;
    private final IMoveCommand moveNotValidCommand;

    private final IMediator mediator;
    private final IPossibleActionList possibleActionList;
    private final IBoard board;

    public MovesRepository(final IMediator mediator,
                           final IPossibleActionList possibleActionList,
                           final IBoard board) {
        this.mediator = mediator;
        this.possibleActionList = possibleActionList;
        this.board = board;

        allowedMoveCommand = new AllowedMoveCommand(mediator, possibleActionList, board);
        checkMoveCommand = new CheckMoveCommand(mediator, possibleActionList, board);
        moveNotValidCommand = new MoveNotValidMoveCommand(mediator, possibleActionList, board);

        actionCommandMap = new HashMap<MoveState, IMoveCommand>() {
            {
                put(MoveState.ALLOWED, allowedMoveCommand);
                put(MoveState.NOT_ALLOWED_CHECK, checkMoveCommand);
                put(MoveState.NOT_ALLOWED_MOVE_VALID, moveNotValidCommand);
            }
        };
    }

    public IMoveCommand getCommand(final MoveState state) {
        return actionCommandMap.get(state);
    }
}
