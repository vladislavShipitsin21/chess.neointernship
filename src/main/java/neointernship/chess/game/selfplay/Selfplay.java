package neointernship.chess.game.selfplay;

import neointernship.chess.game.gameplay.init.GameInitializerForBots;

public class  Selfplay implements ISelfplay{

    @Override
    public void start() {
        for (int i = 0; i < 50; i++) {
            System.out.println("Game " + i);
            final GameInitializerForBots gameInitializerForBots = new GameInitializerForBots(i);
            gameInitializerForBots.start();
        }
    }
}
