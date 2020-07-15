package neointernship.chess.game.gameplay.gameprocesscontroller;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.moveaction.MoveCorrectnessValidator;
import neointernship.chess.game.gameplay.moveaction.commands.IMoveCommand;
import neointernship.chess.game.gameplay.moveaction.movesrepository.MovesRepository;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.MoveState;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.logger.IGameLogger;

public class GameProcessController implements IGameProcessController {
    private final IMediator mediator;
    private final IPossibleActionList possibleActionList;
    private final IBoard board;

    private final MovesRepository movesRepository;
    private final MoveCorrectnessValidator moveCorrectnessValidator;


    private boolean playerDidMove;

    public GameProcessController(final IMediator mediator,
                                 final IPossibleActionList possibleActionList,
                                 final IBoard board,
                                 final IGameLogger gameLogger) {
        this.mediator = mediator;
        this.possibleActionList = possibleActionList;
        this.board = board;

        movesRepository = new MovesRepository(mediator, possibleActionList, board);
        moveCorrectnessValidator = new MoveCorrectnessValidator(mediator, possibleActionList, board);
    }

    @Override
    public void makeTurn(final IPlayer player, final IAnswer answer) {
        final MoveState moveState = moveCorrectnessValidator.check(player.getColor(), answer);
        final IMoveCommand moveCommand = movesRepository.getCommand(moveState);
        playerDidMove = moveCommand.execute(player, answer);
    }

    @Override
    public boolean playerDidMove() {
        return playerDidMove;
    }
}
