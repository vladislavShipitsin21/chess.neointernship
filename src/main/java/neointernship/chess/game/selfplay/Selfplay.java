package neointernship.chess.game.selfplay;

public class Selfplay {
    public static void start(int countGame) {
        for (int i = 0; i < countGame; i++) {
            System.out.println("Game " + i);
            GameInitializer gameInitializer = new GameInitializer(i);
            gameInitializer.start();
        }
    }

    public static void main(String[] args) {
        start(100);
    }
}
