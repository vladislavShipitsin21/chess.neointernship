package neointernship.chess.game.gameplay.figureactions;

import neointernship.chess.game.gameplay.figureactions.patterns.BasicPatterns;
import neointernship.chess.game.gameplay.figureactions.patterns.IBasicPatterns;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PossibleActionList implements IPossibleActionList, Cloneable {
    private final IMediator mediator;
    private final IBoard board;
    private final IBasicPatterns basicPatterns;

    private final Map<Figure, Collection<IField>> mapFigure;

    public PossibleActionList(final IBoard board,
                              final IMediator mediator) {
        this.board = board;
        this.mediator = mediator;
        this.basicPatterns = new BasicPatterns(mediator, board, this);

        this.mapFigure = new HashMap<>();
    }

    @Override
    public void updateLists() {
        for (Figure figure : mediator.getFigures()) {
            mapFigure.get(figure).clear();
            mapFigure.get(figure).addAll(Intermediary.getList(figure, basicPatterns));
        }
    }

    @Override
    public Map<Figure, Collection<IField>> getMap() {
        return mapFigure; // todo нарушение инкапсуляции данных ( сделать копию) !!!
    }

    @Override
    public void addNewFigure(final Figure figure) {
        mapFigure.put(figure, new ArrayList<>());
    }

    @Override
    public void removeFigure(final Figure figure) {
        mapFigure.remove(figure);
    }

    @Override
    public void clearList(final Figure figure) {
        mapFigure.get(figure).clear();
    }

    @Override
    public Collection<IField> getList(Figure figure) {
        return mapFigure.get(figure);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
