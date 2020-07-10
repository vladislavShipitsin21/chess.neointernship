package neointernship.chess.game.gameplay.gameprocesscontroller;

import neointernship.chess.game.gameplay.moveaction.movesrepository.MovesActionRepository;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.KingState;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;

public class GameProcessController implements IGameProcessController {
    private final IMediator mediator;
    private final IPossibleActionList possibleActionList;
    private final IBoard board;

    private final MovesActionRepository movesActionRepository;

    private boolean playerDidMove;

    public GameProcessController(final IMediator mediator,
                                 final IPossibleActionList possibleActionList,
                                 final IBoard board) {
        this.mediator = mediator;
        this.possibleActionList = possibleActionList;
        this.board = board;

        movesActionRepository = new MovesActionRepository(mediator, possibleActionList, board);
    }

    @Override
    public void makeTurn(final KingState kingState, final IAnswer answer) {
        playerDidMove = movesActionRepository.getCommand(kingState).execute();
    }

    @Override
    public boolean playerDidMove() {
        return playerDidMove;
    }
}
