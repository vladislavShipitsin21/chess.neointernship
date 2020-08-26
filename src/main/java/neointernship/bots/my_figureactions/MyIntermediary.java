package neointernship.bots.my_figureactions;

import neointernship.bots.my_figureactions.my_patterns.my_potential.MyPotentialBasicPatterns;
import neointernship.chess.game.model.figure.piece.*;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.ArrayList;

public class MyIntermediary {

    public static ArrayList<IField> getList(final Figure figure, final MyPotentialBasicPatterns basicAttackPatterns) {
        final ArrayList<IField> list = new ArrayList<>();

        if (Bishop.class.equals(figure.getClass())) {
            return basicAttackPatterns.getDiagonalFields(figure);

        } else if (Queen.class.equals(figure.getClass())) {
            list.addAll(basicAttackPatterns.getDiagonalFields(figure));
            list.addAll(basicAttackPatterns.getHorizonVerticalFields(figure));
            return list;

        } else if (Pawn.class.equals(figure.getClass())) {
            return basicAttackPatterns.getPawnFields(figure);

        } else if (Rook.class.equals(figure.getClass())) {
            return basicAttackPatterns.getHorizonVerticalFields(figure);

        } else if (King.class.equals(figure.getClass())) {
            return basicAttackPatterns.getKingFields(figure);

        } else if (Knight.class.equals(figure.getClass())) {
            return basicAttackPatterns.getKnightFields(figure);
        }
        return list;
    }
}
