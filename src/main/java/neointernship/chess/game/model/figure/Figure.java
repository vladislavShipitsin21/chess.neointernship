package neointernship.chess.game.model.figure;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.actions.attack.IPossibleAttackFieldsRepository;
import neointernship.chess.game.model.figure.actions.move.IPossibleMoveFieldsRepository;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.ArrayList;

/**
 * Интерфейс (абстрактный класс) для описания шахматных фигур.
 */
public abstract class Figure {
    private String name;
    private char gameSymbol;
    private Color color;

    private final IPossibleAttackFieldsRepository possibleAttackListRepository;
    private final IPossibleMoveFieldsRepository possibleMoveListRepository;

    public Figure(final IPossibleAttackFieldsRepository possibleAttackListRepository,
                  IPossibleMoveFieldsRepository possibleMoveListRepository) {
        this.possibleAttackListRepository = possibleAttackListRepository;
        this.possibleMoveListRepository = possibleMoveListRepository;
    }

    public ArrayList<IField> getPossibleMoveFields() {
        return possibleMoveListRepository.getList();
    }

    public ArrayList<IField> getPossibleAttackFields() {
        return possibleAttackListRepository.getList();
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
