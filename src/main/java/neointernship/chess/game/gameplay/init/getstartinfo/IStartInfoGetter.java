package neointernship.chess.game.gameplay.init.getstartinfo;

public interface IStartInfoGetter {
    void setStartInfo();
    String getFirstPlayerName();
    String getSecondPlayerName();
    String getChessType();
}
