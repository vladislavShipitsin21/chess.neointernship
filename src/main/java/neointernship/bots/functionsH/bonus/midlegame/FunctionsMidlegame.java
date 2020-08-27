package neointernship.bots.functionsH.bonus.midlegame;

import neointernship.bots.functionsH.IFunctionsH;
import neointernship.bots.functionsH.bonus.BonusCountFigure;
import neointernship.bots.functionsH.bonus.IBonus;
import neointernship.bots.functionsH.bonus.debut.BonusControlCenter;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;

import java.util.ArrayList;
import java.util.List;

import static neointernship.bots.functionsH.bonus.Bonus.priceOnePawn;
import static neointernship.bots.modeling.Progressing.isTerminal;

public class FunctionsMidlegame implements IFunctionsH {

    private final List<IBonus> bonuses;

    public FunctionsMidlegame() {
        this.bonuses = new ArrayList<>();
        bonuses.add(new BonusCountFigure());

        bonuses.add(new BonusActivePositions(priceOnePawn / 3));
        bonuses.add(new BonusAttackKing(priceOnePawn / 3));
        bonuses.add(new BonusControlCenter(priceOnePawn / 6));
        bonuses.add(new BonusKingPawn(priceOnePawn / 6));
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
