package neointernship.chess.game.model.mediator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import neointernship.chess.client.communication.serializer.FieldDeserializer;
import neointernship.chess.client.communication.serializer.FieldSerializer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.figure.piece.Pawn;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Связка клетка-фигура.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Mediator implements IMediator, Cloneable {

    @JsonSerialize(keyUsing = FieldSerializer.class)
    @JsonDeserialize(keyUsing = FieldDeserializer.class)
    private final HashMap<IField, Figure> mediator;

    //@JsonCreator
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

    @JsonCreator
    public Mediator(@JsonProperty("mediator") final HashMap<IField, Figure> mediator){
        this.mediator = mediator;
    }

    //@JsonCreator
    public Mediator(final String string) {
        //mediator = new HashMap<>();
        this();
        for (final String maps1 : string.split("<")){
            for (final String maps2: maps1.split(">")) {
                final String[] maps3 = maps2.split(";");
                if (maps3.length == 2){
                    final IField field = new Field(maps3[0]);
                    final Figure figure =  new Figure(maps3[1]) {};
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

   /* @Override
    @JsonValue
    public String toString() {
        String string = "";
        for (final IField field: mediator.keySet()) {
            string += "<" + field.toString() + ";" + mediator.get(field).toString() + ">";
        }
        return string;
    }*/

    public static String serializer(final Mediator initGame) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(initGame);
    }

    public static Mediator deserializer(final String string) throws JsonProcessingException {
        return new ObjectMapper().readValue(string, Mediator.class);
    }

    public static void main(String[] args) throws JsonProcessingException {
        Mediator mediator = new Mediator();
        //mediator.addNewConnection(new Field(1, 3), new King(Color.BLACK));
        mediator.addNewConnection(new Field(1, 2), new Pawn(Color.BLACK));
        String s = serializer(mediator);
        System.out.println(s);

        Mediator mediator1 = deserializer(s);
        System.out.println();
    }
}