package neointernship.chess.game.model.mediator;

import com.fasterxml.jackson.annotation.JsonValue;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.factory.Factory;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Связка клетка-фигура.
 */
public class Mediator implements IMediator, Cloneable {

    private final HashMap<IField, Figure> mediator;

    public Mediator() {
        mediator = new HashMap<>();
    }

    public Mediator(final IMediator mediator) {
        this();
        for (final Figure figure : mediator.getFigures()){
            final IField field = mediator.getField(figure);
            addNewConnection(field,figure);
        }
    }

    //@JsonCreator
    public Mediator(final String string) {
        this();
        final Factory factory = new Factory();
        for (final String maps1 : string.split("<")){
            for (final String maps2: maps1.split(">")) {
                final String[] maps3 = maps2.split(";");
                if (maps3.length == 2){
                    final IField field = new Field(maps3[0]);
                    String[] maps4 = maps3[1].split(":");
                    Figure figure = factory.createFigure(maps4[1].trim().charAt(0), Color.parseColor(maps4[2].trim()));
                    this.addNewConnection(field, figure);
                }
            }
        }
    }

    /**
     * Добавление новой связи
     *
     * @param field поле
     * @param figure фигура
     */
    @Override
    public void addNewConnection(final IField field, final Figure figure) {
        mediator.put(field, figure);
    }

    @Override
    public void deleteConnection(final IField field) {
        mediator.remove(field);
    }

    @Override
    public void updateConnection(IField field, Figure figure) {
        mediator.replace(field, figure);
    }

    @Override
    public void clear() {
        mediator.clear();
    }

    @Override
    public Figure getKing(final Color color) {
        for (final Figure figure : mediator.values()) {
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
    public Collection<Figure> getFigures(final Color color) {
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
        final Set<Map.Entry<IField, Figure>> entrySet = mediator.entrySet();

        for (final Map.Entry<IField, Figure> pair : entrySet) {
            if (Objects.equals(figure, pair.getValue())) {
                return pair.getKey();
            }
        }
        return null;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    @JsonValue
    public String toString() {
        String string = "";
        for (final IField field: mediator.keySet()) {
            string += "<" + field.toString() + ";" + mediator.get(field).toString() + ">";
        }
        return string;
    }
}