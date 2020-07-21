package neointernship.chess.game.model.subscriber;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.KingState;

public interface ISubscriber {
    void update(final Color color, final KingState kingState);
}
