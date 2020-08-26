package neointernship.bots.functionsH.bonus;

import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.*;
import neointernship.chess.game.model.mediator.IMediator;

import java.util.HashMap;

public class BonusDraw extends Bonus {

    private HashMap<Class, Integer> classMap;

    protected BonusDraw(final double price) {
        super(price);
    }

    @Override
    public double getBonus(final Position position, final Color playerColor) {
        return 0;
    }
}
