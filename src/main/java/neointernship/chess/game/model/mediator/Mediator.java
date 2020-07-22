package neointernship.chess.game.model.mediator;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Связка клетка-фигура.
 */
public class Mediator implements IMediator, Cloneable {
    private HashMap<IField, Figure> mediator;

    public Mediator() {
        mediator = new HashMap<>();
    }

    public Mediator(IMediator mediator) {
        this();
        for (Figure figure : mediator.getFigures()){
            IField field = mediator.getField(figure);
            addNewConnection(field,figure);
        }
    }


    /**
     * Добавление новой связи
     *
     * @param field поле
     * @param figure фигура
     */
    @Override
    public void addNewConnection(final IField field,
                                 final Figure figure) {
        mediator.put(field, figure);
    }

    @Override
    public void deleteConnection(final IField field) {
        mediator.remove(field);
    }

    @Override
    public void clear() {
        mediator.clear();
    }

    @Override
    public Figure getKing(Color color) {
        for (Figure figure : mediator.values()) {
            if (figure.getClass().equals(King.class) && figure.getColor() == color) {
                return figure;
            }
        }
        return null;
    }

    /**
     * Получение фигуры, стоящей на данном поле.
     * @param field - входящее поле.
     * @return фигура или null.
     */
    public Figure getFigure(final IField field) {
        return mediator.get(field);
    }

    @Override
    public Collection<Figure> getFigures(Color color) {
        return getFigures()
                .stream()
                .filter(f -> f.getColor() == color)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает все фигуры.
     * @return колекция фигур или null
     */
    public Collection<Figure> getFigures() {
        return mediator.values();
    }

    /**
     * Поиск поля, на котором стоит данная фигура.
     * @param figure - входимая фигура.
     * @return поле.
     */
    public IField getField(final Figure figure) {
        Set<Map.Entry<IField, Figure>> entrySet = mediator.entrySet();

        for (Map.Entry<IField, Figure> pair : entrySet) {
            if (Objects.equals(figure, pair.getValue())) {
                return pair.getKey();
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mediator mediator1 = (Mediator) o;
        return Objects.equals(mediator, mediator1.mediator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mediator);
    }
}