package neointernship.chess.game.gameplay.moveaction.commands;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.board.IBoard;

public class RestrictMoveCommand implements IMoveCommand {
    private final IMediator mediator;
    private final IPossibleActionList possibleActionList;
    private final IBoard board;

    public RestrictMoveCommand(final IMediator mediator,
                               final IPossibleActionList possibleActionList,
                               final IBoard board) {
        this.mediator = mediator;
        this.possibleActionList = possibleActionList;
        this.board = board;
    }

    @Override
    public boolean execute(IPlayer player, IAnswer answer) {
        System.out.println("Move is not Valid (?!)");

        return false;
    }
}
