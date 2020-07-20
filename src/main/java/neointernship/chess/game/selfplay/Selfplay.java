package neointernship.chess.game.selfplay;

import neointernship.chess.game.gameplay.init.GameInitializer;

public class  Selfplay implements ISelfplay{


    @Override
    public void start(int countGame) {
        for (int i = 0; i < countGame; i++) {
            System.out.println("Game " + i);
            GameInitializer gameInitializer = new GameInitializer(i);
            gameInitializer.start();
        }
    }
}
