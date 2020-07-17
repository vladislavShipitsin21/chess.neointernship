package neointernship.chess.game.selfplay;

import neointernship.chess.game.gameplay.init.GameInitializerForBots;

public class  Selfplay implements ISelfplay{


    @Override
    public void start(int countGame) {
        for (int i = 0; i < countGame; i++) {
            System.out.println("Game " + i);
            final GameInitializerForBots gameInitializerForBots = new GameInitializerForBots(i);
            gameInitializerForBots.start();
        }
    }
}
