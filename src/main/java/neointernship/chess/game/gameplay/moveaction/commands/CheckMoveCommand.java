package neointernship.chess.game.gameplay.moveaction.commands;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.logger.IGameLogger;

/**
 * Проверка хода в ситуации шаха
 */
public class CheckMoveCommand implements IMoveCommand {

    private final IMediator mediator;
    private final IPossibleActionList possibleActionList;
    private final IBoard board;

    public CheckMoveCommand(final IMediator mediator,
                            final IPossibleActionList possibleActionList,
                            final IBoard board) {
        this.mediator = mediator;
        this.possibleActionList = possibleActionList;
        this.board = board;
    }

    @Override
    public boolean execute(final IPlayer player, final IAnswer answer, final IGameLogger gameLogger) {
        final IField startField = board.getField(answer.getStartX(), answer.getStartY());
        final Figure figure = mediator.getFigure(startField);

        gameLogger.logPlayerWrongAction(player, figure, startField);

        return false;
    }
}
