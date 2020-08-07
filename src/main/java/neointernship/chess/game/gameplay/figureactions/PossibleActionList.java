package neointernship.chess.game.gameplay.figureactions;

import neointernship.chess.game.gameplay.figureactions.patterns.potential.IPotentialBasicPatterns;
import neointernship.chess.game.gameplay.figureactions.patterns.potential.PotentialBasicPatterns;
import neointernship.chess.game.gameplay.figureactions.patterns.real.IRealBasicPatterns;
import neointernship.chess.game.gameplay.figureactions.patterns.real.RealBasicPatterns;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.game.story.StoryGame;

import java.util.*;

public class PossibleActionList implements IPossibleActionList {
    private final IBoard board;
    private final IMediator mediator;
    private final IStoryGame storyGame;

    private final IPotentialBasicPatterns potentialPatterns;
    private final IRealBasicPatterns realPatterns;

    private final Map<Figure, Collection<IField>> realFigureActions;
    private final Map<Figure, Collection<IField>> potentialFigureAction;

    public PossibleActionList(final IBoard board,
                              final IMediator mediator,
                              final IStoryGame storyGame) {
        this.board = board;
        this.mediator = mediator;
        this.storyGame = storyGame;

        this.potentialPatterns = new PotentialBasicPatterns(mediator, board, storyGame);
        this.realPatterns = new RealBasicPatterns(mediator, board, storyGame);

        this.realFigureActions = new HashMap<>();
        this.potentialFigureAction = new HashMap<>();
    }

    public PossibleActionList(final IBoard board,
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


    public void addRealField(final Figure figure, final IField finishField){
        if(realFigureActions.isEmpty()) {
            realFigureActions.put(figure,new ArrayList<>());
        }
        realFigureActions.get(figure).add(finishField);
    }
    public void clearRealList(final Figure figure, final IField finishField){
        if(realFigureActions.isEmpty()) {
            realFigureActions.put(figure,new ArrayList<>());
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

    @Override
    public void updatePotentialLists() {
        potentialFigureAction.clear();

        for (Figure figure : mediator.getFigures()) {
            potentialFigureAction.put(figure, new ArrayList<>());

            potentialFigureAction.get(figure).addAll(Intermediary.getList(figure, potentialPatterns));
        }
    }

    @Override
    public void updatePotentialLists(Color color) {
        for (Figure figure : mediator.getFigures(color)) {
            potentialFigureAction.put(figure, new ArrayList<>());

            potentialFigureAction.get(figure).addAll(Intermediary.getList(figure, potentialPatterns));
        }
    }

    @Override
    public Collection<IField> getRealList(Figure figure) {
        return realFigureActions.get(figure);
    }

    private static int count = 0;
    @Override
    public void updateRealLists() {
        updatePotentialLists();
        realFigureActions.clear();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PossibleActionList that = (PossibleActionList) o;
        return Objects.equals(realFigureActions, that.realFigureActions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mediator, realFigureActions);
    }
}
