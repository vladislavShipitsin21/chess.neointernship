package neointernship.chess.game.model.figure.factory;

import neointernship.chess.game.model.figure.Figure;

public interface IFigureFactory {
    /**
     *
     * @param pieceName
     * @return
     */
    Figure createFigure(final Character pieceName);
}
