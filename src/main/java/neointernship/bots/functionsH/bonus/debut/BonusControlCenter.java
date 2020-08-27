package neointernship.bots.functionsH.bonus.debut;

import neointernship.bots.functionsH.bonus.Bonus;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Bishop;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.Knight;
import neointernship.chess.game.model.figure.piece.Pawn;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * борьба за центр
 */
public class BonusControlCenter extends Bonus {

    private final static int MAX_ACTIVE = 6;

    private final List<IField> fieldsCenterWhite;
    private final List<IField> fieldsCenterBlack;
    private final List<IField> fieldAllCenter;

    private List<Class> hoNeedControl;

    public BonusControlCenter(final double price) {
        super(price);

        fieldAllCenter = new ArrayList<>();
        fieldsCenterWhite = new ArrayList<>();
        fieldsCenterBlack = new ArrayList<>();


        fieldsCenterWhite.add(BOARD.getField(4, 3));
        fieldsCenterWhite.add(BOARD.getField(4, 4));

        fieldsCenterBlack.add(BOARD.getField(3, 3));
        fieldsCenterBlack.add(BOARD.getField(3, 4));

        fieldAllCenter.addAll(fieldsCenterWhite);
        fieldAllCenter.addAll(fieldsCenterBlack);

        hoNeedControl = new ArrayList<>();
        hoNeedControl.add(Pawn.class);
        hoNeedControl.add(Knight.class);
        hoNeedControl.add(Bishop.class);
    }

    @Override
    public double getBonus(final Position position, final Color playerColor) {
        double result = 0;

        final List<IField> currentFieldCenter =
                playerColor == Color.WHITE ? fieldsCenterBlack : fieldsCenterWhite;

        final IMediator mediator = position.getMediator();
        final Collection<Figure> figures = mediator.getFigures();
        final IPossibleActionList possibleActionList = position.getPossibleActionList();

        for (final Figure figure : figures) {
            if(hoNeedControl.contains(figure.getClass())) {
                for (final IField center : currentFieldCenter) {
                    final Collection<IField> list = possibleActionList.getPotentialList(figure);
                    if (list.contains(center)) {
                        result += price / MAX_ACTIVE;
                    }
                }
            }
        }
        return result;
    }
}
