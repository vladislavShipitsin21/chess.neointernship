package neointernship.chess.game.gameplay.moveaction.commands.allow;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.moveaction.commands.IMoveCommand;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.figure.piece.Pawn;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.logger.IGameLogger;

/**
 * Реализация хода в нормальной ситуации
 */
public class AllowMoveCommand implements IMoveCommand {

    private final IMediator mediator;
    private final IPossibleActionList possibleActionList;
    private final IBoard board;

    private final IStoryGame storyGame;

    private final IGameLogger gameLogger;

    private final AttackComand attackComand;
    private final MoveCommand moveCommand;
    private final AisleTakeCommand aisleTakeCommand;
    private final CastlingCommand castlingCommand;
    private final TransformationCommand transformationCommand;


    public AllowMoveCommand(final IMediator mediator,
                            final IPossibleActionList possibleActionList,
                            final IBoard board,
                            final IGameLogger gameLogger,
                            final IStoryGame storyGame) {
        this.mediator = mediator;
        this.possibleActionList = possibleActionList;
        this.board = board;

        this.gameLogger = gameLogger;

        this.storyGame = storyGame;

        attackComand = new AttackComand(board, mediator);
        moveCommand = new MoveCommand(board, mediator);
        aisleTakeCommand = new AisleTakeCommand(board, mediator);
        castlingCommand = new CastlingCommand(board,mediator);
        transformationCommand = new TransformationCommand(board, mediator);
    }


    @Override
    public boolean execute(final IPlayer player, final IAnswer answer) {
        final IField startField = board.getField(answer.getStartX(), answer.getStartY());
        final IField finalField = board.getField(answer.getFinalX(), answer.getFinalY());

        final Figure startFigure = mediator.getFigure(startField);
        final Figure finalFigure = mediator.getFigure(finalField);

         // todo обновление не тут!
        storyGame.update(startFigure);

        IAllowCommand currentComand = null;

        boolean flag = false;

        if(     startFigure.getClass() == Pawn.class &&
                (
                finalField.getXCoord() == board.getSize() - 1 ||
                finalField.getXCoord() == 0
                )
        ){
            currentComand = transformationCommand;
            flag = true;
        }

        if(finalFigure != null && !flag) {
            currentComand = attackComand;
            flag = true;
        }
        if(!flag && startFigure.getClass() == King.class &&
                    Math.abs(startField.getYCoord() - finalField.getYCoord()) > 1){
            currentComand = castlingCommand;
            flag = true;
        }
        if(!flag && startFigure.getClass() == Pawn.class &&
                    Math.abs(startField.getYCoord() - finalField.getYCoord()) == 1){
            currentComand = aisleTakeCommand;
            flag = true;
        }
        if(!flag){
            currentComand = moveCommand;
        }
        currentComand.execute(answer); // делаю ход

        gameLogger.logPlayerMoveAction(player, startFigure, startField, finalField,currentComand);



        possibleActionList.updateRealLists();

        return true;
    }
}
