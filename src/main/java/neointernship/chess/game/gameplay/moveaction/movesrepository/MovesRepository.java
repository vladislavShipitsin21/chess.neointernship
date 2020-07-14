package neointernship.chess.game.gameplay.moveaction.movesrepository;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.moveaction.commands.AllowMoveCommand;
import neointernship.chess.game.gameplay.moveaction.commands.IMoveCommand;
import neointernship.chess.game.gameplay.moveaction.commands.RestrictMoveCommand;
import neointernship.chess.game.model.enums.MoveState;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;

import java.util.HashMap;

public class MovesRepository {
    private final HashMap<MoveState, IMoveCommand> actionCommandMap;
    private final IMoveCommand allowMoveCommand;
    private final IMoveCommand restrictCommand;

    private final IMediator mediator;
    private final IPossibleActionList possibleActionList;
    private final IBoard board;

    public MovesRepository(final IMediator mediator,
                           final IPossibleActionList possibleActionList,
                           final IBoard board) {
        this.mediator = mediator;
        this.possibleActionList = possibleActionList;
        this.board = board;

        allowMoveCommand = new AllowMoveCommand(mediator, possibleActionList, board);
        restrictCommand = new RestrictMoveCommand(mediator, possibleActionList, board);

        actionCommandMap = new HashMap<MoveState, IMoveCommand>() {
            {
                put(MoveState.ALLOWED, allowMoveCommand);
                put(MoveState.RESTRICT, restrictCommand);
            }
        };
    }

    public IMoveCommand getCommand(final MoveState state) {
        return actionCommandMap.get(state);
    }
}
