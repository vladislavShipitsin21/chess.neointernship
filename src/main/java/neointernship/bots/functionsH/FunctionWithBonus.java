package neointernship.bots.functionsH;

import neointernship.bots.functionsH.bonus.*;
import neointernship.bots.functionsH.bonus.debut.BonusControlCenter;
import neointernship.bots.functionsH.bonus.debut.BonusEvolutionFigure;
import neointernship.bots.functionsH.bonus.debut.BonusProtectionKing;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;

import java.util.ArrayList;
import java.util.List;

import static neointernship.bots.functionsH.bonus.Bonus.priceOnePawn;
import static neointernship.bots.modeling.Progressing.isTerminal;

public class FunctionWithBonus {

    private final List<IBonus> bonuses;

    public FunctionWithBonus() {
        this.bonuses = new ArrayList<>();
        bonuses.add(new BonusCountFigure());
        bonuses.add(new BonusControlCenter(priceOnePawn / 2));
        bonuses.add(new BonusEvolutionFigure(priceOnePawn / 4));
        bonuses.add(new BonusProtectionKing(priceOnePawn));
    }

    /**
     * оценивает текущую позицию
     *
     * @param position    позиция
     * @param playerColor цвет игрока, для которого необходимо оценить позицию
     * @return
     */
    public double price(final Position position, final Color playerColor, final IGameState gameState) {
        if (isTerminal(gameState)) {
            if (gameState.getValue() == EnumGameState.MATE) {
                return playerColor == gameState.getColor() ? -1 : 1;
            }
            return 0;
        }
        double result = 0;
        for (final IBonus bonus : bonuses) {
            result += bonus.getBonus(position, playerColor);
        }
        return result;
    }
}
