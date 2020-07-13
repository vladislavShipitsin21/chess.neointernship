package neointernship.chess.game.gameplay.init;


import neointernship.chess.game.gameplay.lobby.GameLobby;
import neointernship.chess.game.gameplay.lobby.ILobby;
import neointernship.chess.game.model.enums.ChessType;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.player.Player;
import neointernship.chess.logger.GameLogger;
import neointernship.chess.logger.IGameLogger;

public class GameInitializer implements IGameInitializer {
    private final ILobby gameLobby;

    public GameInitializer() {
        final IPlayer firstPlayer = new Player("Player 1", Color.WHITE);
        final IPlayer secondPlayer = new Player("Player 2", Color.BLACK);
        final ChessType chessType = ChessType.CLASSIC;

        final IGameLogger gameLogger = new GameLogger(0);

        gameLobby = new GameLobby(firstPlayer, secondPlayer, chessType, gameLogger);
    }

    @Override
    public void start() {
        gameLobby.start();
    }
}
