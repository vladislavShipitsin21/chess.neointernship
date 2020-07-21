package neointernship.chess.game.gameplay.figureactions;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import neointernship.chess.game.gameplay.figureactions.patterns.potential.PotentialPotentialBasicPatterns;
import neointernship.chess.game.gameplay.figureactions.patterns.potential.IPotentialBasicPatterns;
import neointernship.chess.game.gameplay.figureactions.patterns.real.IRealBasicPatterns;
import neointernship.chess.game.gameplay.figureactions.patterns.real.RealBasicPatterns;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PossibleActionList implements IPossibleActionList {
    private final IMediator mediator;

    private final IPotentialBasicPatterns potentialPatterns;
    private final IRealBasicPatterns realPatterns;

    private Map<Figure, Collection<IField>> realFigureActions;
    private Map<Figure, Collection<IField>> potentialFigureAction;

    public PossibleActionList(final IBoard board,
                              final IMediator mediator) {
        this.mediator = mediator;

        this.potentialPatterns = new PotentialPotentialBasicPatterns(mediator, board);
        this.realPatterns = new RealBasicPatterns(mediator, this, board);

        this.realFigureActions = new HashMap<>();
        this.potentialFigureAction = new HashMap<>();
    }

    @Override
    public void updatePotentialLists() {
        potentialFigureAction = new HashMap<>();

        for (Figure figure : mediator.getFigures()) {
            potentialFigureAction.put(figure, new ArrayList<>());

            potentialFigureAction.get(figure).addAll(Intermediary.getList(figure, potentialPatterns));
        }
    }

    @Override
    public Collection<IField> getRealList(Figure figure) {
        return realFigureActions.get(figure);
    }

    @Override
    public void updateRealLists() {
        updatePotentialLists();
        realFigureActions = new HashMap<>();

        for (Figure figure : mediator.getFigures()) {
            realFigureActions.put(figure, new ArrayList<>());

            realFigureActions.get(figure).addAll(realPatterns.getRealMoveList(
                    figure,
                    potentialFigureAction.get(figure)));
        }
    }

    @Override
    public Collection<IField> getPotentialList(Figure figure) {
        return potentialFigureAction.get(figure);
    }

}
