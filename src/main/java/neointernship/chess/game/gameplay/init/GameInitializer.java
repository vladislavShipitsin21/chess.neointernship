package neointernship.chess.game.gameplay.init;

import neointernship.chess.game.gameplay.lobby.GameLobby;
import neointernship.chess.game.gameplay.lobby.ILobby;
import neointernship.chess.game.model.enums.ChessType;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.player.Player;

public class GameInitializer implements IGameInitializer {


    public GameInitializer() {
        /*IPlayer firstPlayer = new Player(message.readLine());
        IPlayer secondPlayer = new Player(message.readLine());
        ChessType chessType = ChessType.CLASSIC; // TODO

        ILobby gameLobby = new GameLobby(firstPlayer, secondPlayer, chessType);

        start(gameLobby);*/
    }

    @Override
    public void start(final ILobby lobby) {
        lobby.start();
    }
}
