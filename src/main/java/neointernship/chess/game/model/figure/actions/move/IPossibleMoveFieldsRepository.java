package neointernship.chess.game.model.figure.actions.move;

import neointernship.chess.game.model.playmap.field.IField;

import java.util.ArrayList;

public interface IPossibleMoveFieldsRepository {
    ArrayList<IField> getList();
    void updateList();
}
