package neointernship.chess.game.gameplay.moveaction.commands;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.gameplay.actions.IPossibleActionList;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.logger.IGameLogger;

public class MoveNotValidMoveCommand implements IMoveCommand {
    private final IMediator mediator;
    private final IPossibleActionList possibleActionList;
    private final IBoard board;

    public MoveNotValidMoveCommand(final IMediator mediator,
                            final IPossibleActionList possibleActionList,
                            final IBoard board) {
        this.mediator = mediator;
        this.possibleActionList = possibleActionList;
        this.board = board;
    }

    @Override
    public boolean execute(final IPlayer player, final IAnswer answer, final IGameLogger gameLogger) {
        final IField startField = board.getField(answer.getStartX(), answer.getStartY());

        gameLogger.logPlayerWrongField(player, startField);

        return false;
    }
}
