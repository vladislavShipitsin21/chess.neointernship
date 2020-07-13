package neointernship.chess.game.gameplay.moveaction.commands;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.logger.IGameLogger;

/**
 * Реализация хода в нормальной ситуации
 */
public class AllowMoveCommand implements IMoveCommand {

    private final IMediator mediator;
    private final IPossibleActionList possibleActionList;
    private final IBoard board;

    public AllowMoveCommand(final IMediator mediator,
                            final IPossibleActionList possibleActionList,
                            final IBoard board) {
        this.mediator = mediator;
        this.possibleActionList = possibleActionList;
        this.board = board;
    }


    @Override
    public boolean execute(IPlayer player, IAnswer answer, IGameLogger gameLogger) {
        IField startField = board.getField(answer.getStartX(), answer.getStartY());
        IField finalField = board.getField(answer.getFinalX(), answer.getFinalY());
        Figure figure = mediator.getFigure(startField);

        mediator.deleteConnection(startField);
        mediator.deleteConnection(finalField);
        mediator.addNewConnection(finalField, figure);
        possibleActionList.updateLists();

        return true;
    }

}
