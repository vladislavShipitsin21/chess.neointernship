package neointernship.chess.game.model.figure.actions;

import neointernship.chess.game.model.figure.actions.patterns.BasicPatterns;
import neointernship.chess.game.model.figure.actions.patterns.IBasicPatterns;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PossibleActionList implements IPossibleActionList {

    private final Map<Figure,Collection<IField>> mapFigire;

    public PossibleActionList() {
        this.mapFigire = new HashMap<>();
    }

    @Override
    public void updateLists(final Figure figure,final IBoard board,final IMediator mediator) {
        IBasicPatterns basicPatterns = new BasicPatterns(mediator, board);
        mapFigire.get(figure).clear();
        mapFigire.get(figure).addAll(Intermediary.getList(figure,basicPatterns));
    }

    @Override
    public Map<Figure, Collection<IField>> getMap() {
        return mapFigire; // todo нарушение инкапсуляции данных ( сделать копию) !!!
    }

    @Override
    public void addNewFigure(final Figure figure) {
        mapFigire.put(figure,new ArrayList<IField>());
    }

    @Override
    public void removeFigura(final Figure figure) {
        mapFigire.remove(figure);
    }

    @Override
    public void clearList(final Figure figure) {
        mapFigire.get(figure).clear();
    }

    @Override
    public Collection<IField> getList(Figure figure) {
        return mapFigire.get(figure);
    }


}
