package neointernship.web.client.GUI.Input;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.web.client.player.PlayerType;

public interface IInput {

    String getUserName() throws InterruptedException;

    Color getColor() throws InterruptedException;

    String getHandShakeAnswer() throws InterruptedException;

    void invise();

    String getMoveAnswer() throws InterruptedException;

    String getTransformAnswer() throws InterruptedException;

    PlayerType getPlayerType() throws InterruptedException;

    void endGame(final EnumGameState enumGameState, final Color color) throws InterruptedException;

    boolean isVoid();
}
