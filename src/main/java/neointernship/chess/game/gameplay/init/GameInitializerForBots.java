package neointernship.chess.game.gameplay.init;

import neointernship.chess.game.gameplay.lobby.GameLobby;
import neointernship.chess.game.gameplay.lobby.ILobby;
import neointernship.chess.game.model.enums.ChessType;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.player.Player;
import neointernship.chess.game.model.player.RandomBot;
import neointernship.chess.logger.GameLogger;
import neointernship.chess.logger.IGameLogger;

public class GameInitializerForBots implements IGameInitializer {
    private final ILobby gameLobby;

    public GameInitializerForBots(int i) {
        IPlayer firstPlayer = new RandomBot(Color.WHITE);
        IPlayer secondPlayer = new RandomBot(Color.BLACK);
        ChessType chessType = ChessType.CLASSIC;

        final IGameLogger gameLogger = new GameLogger(i);

        gameLobby = new GameLobby(firstPlayer, secondPlayer, chessType, gameLogger);
    }


    @Override
    public void start() {
        gameLobby.start();
    }
}
