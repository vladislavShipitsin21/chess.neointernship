package neointernship.chess.game.model.figure.actions.attack;

import neointernship.chess.game.model.playmap.field.IField;

import java.util.ArrayList;

public interface IPossibleAttackFieldsRepository {
    ArrayList<IField> getList();
    void updateList();
}
