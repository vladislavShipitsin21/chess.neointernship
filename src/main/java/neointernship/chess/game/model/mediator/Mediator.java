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
public class Mediator implements IMediator {

    private HashMap<IField, Figure> boardLocationMap;

    public Mediator() {
        boardLocationMap = new HashMap<>();
    }

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

    @Override
    public void deleteConnection(final IField field) {
        boardLocationMap.remove(field);
    }

    @Override
    public void updateConnection(IField field, Figure figure) {
        boardLocationMap.put(field,figure);
    }

    @Override
    public void clear() {
        boardLocationMap.clear();
    }

    @Override
    public King getWhiteKing() {
        return getKing(Color.WHITE);
    }

    @Override
    public King getBlackKing() {
        return getKing(Color.BLACK);
    }

    @Override
    public King getKing(Color color) {
        for(Figure figure : boardLocationMap.values()){
            if(figure.getClass().equals(King.class) && figure.getColor() == color){
                return (King) figure;
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
        return boardLocationMap.get(field);
    }

    @Override
    public Collection<Figure> getFigures(Color color) {
        return getFigures().
                stream().
                filter(f -> f.getColor() == color).
                collect(Collectors.toList());
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