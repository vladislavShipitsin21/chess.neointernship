package neointernship.bots.functionsH.bonus.endgame;

import neointernship.bots.functionsH.IFunctionsH;
import neointernship.bots.functionsH.bonus.Bonus;
import neointernship.bots.functionsH.bonus.BonusCountFigure;
import neointernship.bots.functionsH.bonus.IBonus;
import neointernship.bots.functionsH.bonus.midlegame.BonusAttackKing;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;

import java.util.ArrayList;
import java.util.List;

import static neointernship.bots.functionsH.bonus.Bonus.priceOnePawn;
import static neointernship.bots.modeling.Progressing.isTerminal;

public class FunctonsEndgame implements IFunctionsH {
    private final List<IBonus> bonuses;

    public FunctonsEndgame() {
        this.bonuses = new ArrayList<>();
        bonuses.add(new BonusCountFigure());
        bonuses.add(new BonusAttackKing(priceOnePawn));
        bonuses.add(new BonusActiveKing(priceOnePawn));
        bonuses.add(new BonusTransformate(3 * priceOnePawn));
    }

    /**
     * оценивает текущую позицию
     *
     * @param position    позиция
     * @param playerColor цвет игрока, для которого необходимо оценить позицию
     * @return
     */
    @Override
    public double price(final Position position, final Color playerColor, final IGameState gameState) {
        if (isTerminal(gameState)) {
            if (gameState.getValue() == EnumGameState.MATE) {
                return playerColor == gameState.getColor() ? -100 : 100;
            }
        }
        double result = 0;
        for (final IBonus bonus : bonuses) {
            result += bonus.getBonus(position, playerColor);
        }
        return result;
    }
}
