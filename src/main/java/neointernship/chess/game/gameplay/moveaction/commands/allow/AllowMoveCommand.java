package neointernship.chess.game.gameplay.moveaction.commands.allow;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.moveaction.commands.IMoveCommand;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.figure.piece.Pawn;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.web.client.communication.message.ChessCodes;

/**
 * Реализация хода в нормальной ситуации
 */
public class AllowMoveCommand implements IMoveCommand {

    private final IMediator mediator;
    private final IPossibleActionList possibleActionList;
    private final IBoard board;

    private final IStoryGame storyGame;

    private final IAllowCommand attackComand;
    private final IAllowCommand moveCommand;
    private final IAllowCommand aisleTakeCommand;
    private final IAllowCommand castlingCommand;
    private final IAllowCommand transformationAfterCommand;
    private final IAllowCommand transformationBeforeCommand;


    public AllowMoveCommand(final IMediator mediator,
                            final IPossibleActionList possibleActionList,
                            final IBoard board,
                            final IStoryGame storyGame) {
        this.mediator = mediator;
        this.possibleActionList = possibleActionList;
        this.board = board;

        this.storyGame = storyGame;

        attackComand = new AttackCommand(board, mediator);
        moveCommand = new MoveCommand(board, mediator);
        aisleTakeCommand = new AisleTakeCommand(board, mediator);
        castlingCommand = new CastlingCommand(board,mediator);
        transformationAfterCommand = new TransformationAfterCommand(board, mediator);
        transformationBeforeCommand = new TransformationBeforeCommand(board, mediator);
    }


    @Override
    public ChessCodes execute(final Color color, final IAnswer answer) {
        final IField startField = board.getField(answer.getStartX(), answer.getStartY());
        final IField finalField = board.getField(answer.getFinalX(), answer.getFinalY());

        final Figure startFigure = mediator.getFigure(startField);
        final Figure finalFigure = mediator.getFigure(finalField);

        storyGame.update(startFigure);

        final IAllowCommand currentCommand = getCommand(startFigure,startField,finalFigure,finalField);
        currentCommand.execute(answer); // делаю ход

        possibleActionList.updateRealLists();

        return currentCommand.getChessCode();
    }

    private IAllowCommand getCommand(final Figure startFigure,final IField startField,final Figure finalFigure,final IField finalField){
        if(     startFigure.getClass() == Pawn.class &&
                (
                        finalField.getXCoord() == board.getSize() - 1 ||
                                finalField.getXCoord() == 0
                )
        ){
            return transformationAfterCommand;
        }

        if(     startFigure.getClass() == Pawn.class &&
                (
                        startField.getXCoord() == board.getSize() - 1 ||
                                startField.getXCoord() == 0
                )
        ){
            return transformationBeforeCommand;
        }

        if(finalFigure != null) {
            return attackComand;
        }
        if(startFigure.getClass() == King.class &&
                Math.abs(startField.getYCoord() - finalField.getYCoord()) > 1){
            return castlingCommand;
        }
        if(startFigure.getClass() == Pawn.class &&
                Math.abs(startField.getYCoord() - finalField.getYCoord()) == 1){
            return aisleTakeCommand;
        }
        return moveCommand;
    }
}
