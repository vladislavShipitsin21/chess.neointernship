package neointernship.bots.functionsH.bonus.endgame;

import neointernship.bots.functionsH.IFunctionsH;
import neointernship.bots.functionsH.bonus.BonusCountFigure;
import neointernship.bots.functionsH.bonus.IBonus;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;

import java.util.ArrayList;
import java.util.List;

import static neointernship.bots.modeling.Progressing.isTerminal;

public class FunctonsEndgame implements IFunctionsH {
    private final List<IBonus> bonuses;

    public FunctonsEndgame() {
        this.bonuses = new ArrayList<>();
        bonuses.add(new BonusCountFigure());
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
