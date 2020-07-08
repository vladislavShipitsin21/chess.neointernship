package neointernship.chess.game.model.mediator;

import neointernship.chess.game.model.figure.Figure;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.*;

/**
 * Связка клетка-фигура.
 */
public class Mediator {
    private HashMap<IField, Figure> boardLocationMap;

    /**
     * Static class for creating a Mediator instance.
     */
    private static class MediatorHolder {
        public static final Mediator HOLDER_INSTANCE = new Mediator();
    }

    /**
     * Method for getting singleton Mediator instance.
     * @return {@link Mediator} instance.
     */
    public static Mediator getInstance() {
        return MediatorHolder.HOLDER_INSTANCE;
    }

    /**
     * Получение фигуры, стоящей на данном поле.
     * @param field - входящее поле.
     * @return фигура или null.
     */
    public Figure getFigure(final IField field) {
        return boardLocationMap.get(field);
    }

    /**
     * Возвращает все фигуры.
     * @return колекция фигур или null
     */
    public Collection<Figure> getFigures(){
        return boardLocationMap.values();
    }

    /**
     * Поиск поля, на котором стоит данная фигура.
     * @param figure - входимая фигура.
     * @return поле.
     */
    public IField getField(final Figure figure) {
        Set<Map.Entry<IField, Figure>> entrySet = boardLocationMap.entrySet();

        for (Map.Entry<IField, Figure> pair : entrySet) {
            if (Objects.equals(figure, pair.getValue())) {
                return pair.getKey();
            }
        }
        return null;
    }
}
