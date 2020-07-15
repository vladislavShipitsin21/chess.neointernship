package neointernship.chess.game.gameplay.loop;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.lobby.GameLobby;
import neointernship.chess.game.model.enums.ChessType;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.logger.IGameLogger;

public class GameLoopModeling extends GameLoop implements IGameLoop {

    public GameLoopModeling(IMediator mediator, IPossibleActionList possibleActionList, IBoard board, IPlayer firstPlayer, IPlayer secondPlayer, IGameLogger gameLogger) {
        super(mediator, possibleActionList, board, firstPlayer, secondPlayer, gameLogger);

    }

}
