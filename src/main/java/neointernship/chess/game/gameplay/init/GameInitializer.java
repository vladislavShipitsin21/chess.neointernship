package neointernship.chess.game.gameplay.init;

import neointernship.chess.game.gameplay.lobby.GameLobby;
import neointernship.chess.game.gameplay.lobby.ILobby;
import neointernship.chess.game.model.enums.ChessType;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.player.RandomBot;

public class GameInitializer implements IGameInitializer {
    private final ILobby gameLobby;

    public GameInitializer() {
        IPlayer firstPlayer = new RandomBot();
        IPlayer secondPlayer = new RandomBot();
        ChessType chessType = ChessType.CLASSIC;

        gameLobby = new GameLobby(firstPlayer, secondPlayer, chessType);
    }

    @Override
    public void start() {
        gameLobby.start();
    }
}
