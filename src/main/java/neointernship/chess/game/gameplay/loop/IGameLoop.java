package neointernship.chess.game.gameplay.loop;

import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.board.IBoard;

public interface IGameLoop {
    void activate(final IMediator mediator,
                  final IPossibleActionList possibleActionList,
                  final IBoard board,
                  final IPlayer firstPlayer,
                  final IPlayer secondPlayer);
}
