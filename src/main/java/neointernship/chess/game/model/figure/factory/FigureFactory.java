package neointernship.chess.game.model.figure.factory;

import neointernship.chess.game.model.figure.Figure;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class FigureFactory implements IFigureFactory {
    private PieceClassesRepository pieceClassesRepository;

    /**
     * Constructor of the class, where we declare the PieceRepository instance.
     */
    public FigureFactory() {
        pieceClassesRepository = new PieceClassesRepository();
    }

    /**
     * This method creates and transfers an Figure instances in according to the input piece game symbol.
     *
     * @param pieceName {@link Character} input piece name.
     * @return IPiece instance.
     */
    @Override
    public Figure createFigure(final Character pieceName) {
        Class<? extends Figure> pieceClass = pieceClassesRepository.getPieceClass(pieceName);
        try {
            Constructor<? extends Figure> constructor = pieceClass.getConstructor();
            return constructor.newInstance(); // todo нужнен ли здесь цвет фигур ?
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
