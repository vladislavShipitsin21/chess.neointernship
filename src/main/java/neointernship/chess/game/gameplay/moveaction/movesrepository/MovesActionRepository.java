package neointernship.chess.game.gameplay.moveaction.movesrepository;

import neointernship.chess.game.gameplay.moveaction.CheckActionCommand;
import neointernship.chess.game.gameplay.moveaction.IMoveActionCommand;
import neointernship.chess.game.gameplay.moveaction.NormalActionCommand;
import neointernship.chess.game.model.enums.KingState;

import java.util.HashMap;

public class MovesActionRepository {
    private final HashMap<KingState, IMoveActionCommand> actionCommandMap;
    private final IMoveActionCommand normalActionCommand;
    private final IMoveActionCommand checkActionCommand;

    public MovesActionRepository() {
        normalActionCommand = new NormalActionCommand();
        checkActionCommand = new CheckActionCommand();

        actionCommandMap = new HashMap<KingState, IMoveActionCommand>() {
            {
                put(KingState.FREE, normalActionCommand);
                put(KingState.CHECK, checkActionCommand);
            }
        };
    }

    public IMoveActionCommand getCommand(final KingState state) {
        return actionCommandMap.get(state);
    }
}
