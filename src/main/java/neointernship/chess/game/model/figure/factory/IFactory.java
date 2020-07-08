package neointernship.chess.game.model.figure.factory;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.Figure;

public interface IFactory {
    Figure createNewFigure(final Character figureSymbol, final Color color);
}
