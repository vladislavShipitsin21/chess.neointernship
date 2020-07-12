package neointernship.chess.game.gameplay.init;


import neointernship.chess.game.gameplay.lobby.GameLobby;
import neointernship.chess.game.gameplay.lobby.ILobby;
import neointernship.chess.game.model.enums.ChessType;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.player.Player;

public class GameInitializer implements IGameInitializer {
    private final ILobby gameLobby;

    public GameInitializer() {
        IPlayer firstPlayer = new Player("Player 1", Color.WHITE);
        IPlayer secondPlayer = new Player("Player 2", Color.BLACK);
        ChessType chessType = ChessType.CLASSIC;

        gameLobby = new GameLobby(firstPlayer, secondPlayer, chessType);
    }

    @Override
    public void start() {
        gameLobby.start();
    }
}
