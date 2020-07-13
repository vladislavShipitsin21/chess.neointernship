package neointernship.chess.game.gameplay.init;

import neointernship.chess.game.gameplay.lobby.GameLobby;
import neointernship.chess.game.gameplay.lobby.ILobby;
import neointernship.chess.game.model.enums.ChessType;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.player.Player;
import neointernship.chess.game.model.player.RandomBot;

public class GameInitializerForBots implements IGameInitializer {
    private final ILobby gameLobby;

    public GameInitializerForBots() {
        IPlayer firstPlayer = new RandomBot(Color.WHITE);
        IPlayer secondPlayer = new RandomBot(Color.BLACK);
        ChessType chessType = ChessType.CLASSIC;

        gameLobby = new GameLobby(firstPlayer, secondPlayer, chessType);
    }


    @Override
    public void start() {
        gameLobby.start();
    }
}
