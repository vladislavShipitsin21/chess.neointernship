package neointernship.selfplay;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.player.*;

enum TypeBot {
    RANDOM,
    EXPECTIMAX,
    MINI_MAX,
    MINI_MAX_AB,
    MINI_MAX_THREAD;
}
public class FactoryBot {
    public static Bot getBot(final TypeBot typeBot, final Color color,final int maxDepth){
        switch (typeBot){
            case RANDOM: return new RandomBot(color);
            case EXPECTIMAX: return new ExpectiMaxBot(color,maxDepth);
            case MINI_MAX: return new MiniMaxBot(color,maxDepth);
            case MINI_MAX_AB: return new MiniMaxAB(color,maxDepth);
            case MINI_MAX_THREAD: return new MiniMaxThreadBot(color, maxDepth);
        }
        return null;
    }
}
