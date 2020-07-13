package neointernship.chess.game.gameplay.actions;

import neointernship.chess.game.gameplay.actions.patterns.BasicPatterns;
import neointernship.chess.game.gameplay.actions.patterns.IBasicPatterns;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PossibleActionList implements IPossibleActionList {
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
        initMap();
    }

    private void initMap(){
        for (final Figure figure : mediator.getFigures()) {
           mapFigure.put(figure,new ArrayList<>());
        }
    }

    @Override
    public void updateLists() {
        mapFigure.clear();
        initMap();

        for (final Figure figure : mediator.getFigures()) {
            mapFigure.get(figure).addAll(Intermediary.getList(figure, basicPatterns));
        }
    }

    @Override
    public Collection<IField> getList(Figure figure) {
        return mapFigure.get(figure);
    }

}
