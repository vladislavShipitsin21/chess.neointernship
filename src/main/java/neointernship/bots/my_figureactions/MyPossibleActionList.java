package neointernship.bots.my_figureactions;

import neointernship.bots.my_figureactions.my_patterns.my_potential.MyPotentialBasicPatterns;
import neointernship.bots.my_figureactions.my_patterns.my_real.MyRealBasicPatterns;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;

import java.util.*;

public class MyPossibleActionList implements IPossibleActionList {
    private final IBoard board;
    private final IMediator mediator;
    private final IStoryGame storyGame;

    private final MyPotentialBasicPatterns potentialPatterns;
    private final MyRealBasicPatterns realPatterns;

    private final Map<Figure, Collection<IField>> realFigureActions;
    private final Map<Figure, Collection<IField>> potentialFigureAction;

    public MyPossibleActionList(final IBoard board,
                                final IMediator mediator,
                                final IStoryGame storyGame) {
        this.board = board;
        this.mediator = mediator;
        this.storyGame = storyGame;

        this.potentialPatterns = new MyPotentialBasicPatterns(mediator, board, storyGame);
        this.realPatterns = new MyRealBasicPatterns(mediator, board, storyGame);

        this.realFigureActions = new HashMap<>();
        this.potentialFigureAction = new HashMap<>();
    }

    /*public PossibleActionList(final IBoard board,
                              final IMediator mediator,
                              final IStoryGame storyGame,
                              final Map<Figure, Collection<IField>> realFigureActions,
                              final Map<Figure, Collection<IField>> potentialFigureAction) {
        this.board = board;
        this.mediator = mediator;
        this.storyGame = storyGame;

        this.potentialPatterns = new PotentialBasicPatterns(mediator, board, storyGame);
        this.realPatterns = new RealBasicPatterns(mediator, board, storyGame);

        this.realFigureActions = new HashMap<>(realFigureActions);
        this.potentialFigureAction = new HashMap<>(potentialFigureAction);
    }
*/

    public void addRealField(final Figure figure, final IField finishField) {
        if (realFigureActions.isEmpty()) {
            realFigureActions.put(figure, new ArrayList<>());
        }
        realFigureActions.get(figure).add(finishField);
    }

    public void clearRealList(final Figure figure, final IField finishField) {
        if (realFigureActions.isEmpty()) {
            realFigureActions.put(figure, new ArrayList<>());
        }
        realFigureActions.get(figure).add(finishField);
    }

    public Map<Figure, Collection<IField>> getRealFigureActions() {
        return realFigureActions;
    }

    public Map<Figure, Collection<IField>> getPotentialFigureAction() {
        return potentialFigureAction;
    }

    public IStoryGame getStoryGame() {
        return storyGame;
    }


    public void updatePotentialLists() {
        potentialFigureAction.clear();

        for (final Figure figure : mediator.getFigures()) {
            potentialFigureAction.put(figure, new ArrayList<>());

            potentialFigureAction.get(figure).addAll(MyIntermediary.getList(figure, potentialPatterns));
        }
    }

    public void updatePotentialLists(final Color color) {
        for (final Figure figure : mediator.getFigures(color)) {
            potentialFigureAction.put(figure, new ArrayList<>());

            potentialFigureAction.get(figure).addAll(MyIntermediary.getList(figure, potentialPatterns));
        }
    }


    public Collection<IField> getRealList(final Figure figure) {
        return realFigureActions.get(figure);
    }


    public void updateRealLists() {
        updatePotentialLists();
        realFigureActions.clear();

        for (final Figure figure : mediator.getFigures()) {
            realFigureActions.put(figure, new ArrayList<>());

            realFigureActions.get(figure).addAll(realPatterns.getRealMoveList(
                    figure,
                    potentialFigureAction.get(figure)));
        }
    }


    public Collection<IField> getPotentialList(final Figure figure) {
        return potentialFigureAction.get(figure);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final MyPossibleActionList that = (MyPossibleActionList) o;
        return Objects.equals(realFigureActions, that.realFigureActions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mediator, realFigureActions);
    }
}
