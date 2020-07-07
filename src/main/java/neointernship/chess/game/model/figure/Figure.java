package neointernship.chess.game.model.figure;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.field.IField;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Интерфейс (абстрактный класс) для описания шахматных фигур.
 */
public abstract class Figure {
    private String name;
    private char gameSymbol;
    private Color color;

    public IField getField() {
        return Mediator.getInstance().getField(this);
    }

    public ArrayList<IField> getPossibleAttackList() {
        return null;
    }

    public ArrayList<IField> getPossibleMoveList() {
        return null;
    }

    boolean isAlive() {
        return Mediator.getInstance().getField(this) != null;
    }

    public String getName() {
        return name;
    }

    public char getGameSymbol() {
        return gameSymbol;
    }

    public Color getColor() {
        return color;
    }
}
