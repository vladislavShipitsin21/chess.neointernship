package neointernship.chess.game.selfplay;

import neointernship.chess.game.model.enums.ChessType;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.player.RandomBot;
import neointernship.chess.logger.GameLogger;
import neointernship.chess.logger.IGameLogger;
import neointernship.web.server.Server;

public class GameInitializer {
    private final GameLobby gameLobby;

    public GameInitializer(int numberGame) {

//        final IPlayer firstPlayer = new Player("Player 1", Color.WHITE);
//        final IPlayer secondPlayer = new Player("Player 2", Color.BLACK);
        final IPlayer firstPlayer = new RandomBot(Color.WHITE);
        final IPlayer secondPlayer = new RandomBot(Color.BLACK);
        final ChessType chessType = ChessType.CLASSIC;

        final IGameLogger gameLogger = new GameLogger(numberGame);

        gameLobby = new GameLobby(firstPlayer, secondPlayer, chessType,gameLogger);
    }

    public void start() {
        gameLobby.start();
    }
}
