package neointernship.chess.game.model.mediator;

import neointernship.chess.game.model.figure.Figure;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.*;

/**
 * Связка клетка-фигура.
 */
public class Mediator implements IMediator {
    private HashMap<IField, Figure> boardLocationMap;

    /**
     * Добавление новой связи
     *
     * @param field поле
     * @param figure фигура
     */
    @Override
    public void addNewConnection(final IField field, final Figure figure) {
        boardLocationMap.put(field, figure);
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