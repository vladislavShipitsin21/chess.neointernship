package neointernship.chess.game.gameplay.moveaction.commands;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.logger.IGameLogger;

public class RestrictMoveCommand implements IMoveCommand {
    private final IMediator mediator;
    private final IPossibleActionList possibleActionList;
    private final IBoard board;

    private final IGameLogger gameLogger;

    public RestrictMoveCommand(final IMediator mediator,
                               final IPossibleActionList possibleActionList,
                               final IBoard board,
                               final IGameLogger gameLogger) {
        this.mediator = mediator;
        this.possibleActionList = possibleActionList;
        this.board = board;

        this.gameLogger = gameLogger;
    }

    @Override
    public boolean execute(final IPlayer player, final IAnswer answer) {
        System.out.println("Move is not Valid (?!)");

        final IField startField = board.getField(answer.getStartX(), answer.getStartY());

        gameLogger.logPlayerWrongAction(player, startField);
        return false;
    }
}
