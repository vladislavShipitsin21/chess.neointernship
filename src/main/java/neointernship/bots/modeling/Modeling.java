package neointernship.bots.modeling;

import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.gameplay.moveaction.commands.allow.AllowMoveCommand;
import neointernship.chess.game.gameplay.moveaction.commands.allow.IAllowCommand;
import neointernship.chess.game.model.answer.AnswerSimbol;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.model.util.Pair;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.game.story.StoryGame;

import java.util.*;

public class Modeling implements Iterator<Pair<Position, IAnswer>> {

    private static final IBoard board = new Board();

    private final Position actualPosition;
    private final Color activeColor;

    private final IMediator mediator;
    private final PossibleActionList possibleActionList;

    private final Iterator<Figure> iterFigure;
    private Iterator<IField> iterField;
    private Figure currentFigure;

    public Modeling(final Position actualPosition, final Color activeColor) {
        this.actualPosition = actualPosition;
        this.activeColor = activeColor;

        this.mediator = actualPosition.getMediator();
        this.possibleActionList = actualPosition.getPossibleActionList();

        final Collection<Figure> figures = mediator.getFigures(activeColor);
        iterFigure = figures.iterator();
        currentFigure = iterFigure.next();
        iterField = possibleActionList.getRealList(currentFigure).iterator();
    }

    public static Map<Position, IAnswer> modeling(final Position startPositions, final Color activeColor){
        final IMediator mediator = startPositions.getMediator();
        final PossibleActionList possibleActionList = startPositions.getPossibleActionList();

        final Map<Position, IAnswer> positionsMap = new HashMap<>();
        final Collection<Figure> figures = mediator.getFigures(activeColor);

        for (final Figure figure : figures) {

            for (final IField finishField : possibleActionList.getRealList(figure)) {

                final IField startField = mediator.getField(figure);

                // определить тип хода
                final IMediator newMediator = new Mediator(mediator);
                final IStoryGame newStoryGame = new StoryGame((StoryGame) possibleActionList.getStoryGame());
                final PossibleActionList newPossibleActionList = new PossibleActionList(board, newMediator, newStoryGame);

                final AllowMoveCommand allowMoveCommand =
                        new AllowMoveCommand(newMediator, newPossibleActionList, board, newStoryGame);

                final IAllowCommand command = allowMoveCommand.getCommand(startField, finishField);

                final IAnswer answer = new AnswerSimbol(
                        startField.getXCoord(),
                        startField.getYCoord(),
                        finishField.getXCoord(),
                        finishField.getYCoord(),
                        'Q');

                // перейти к следующему ходу
                command.execute(answer);

                newStoryGame.update(figure);
                newPossibleActionList.updateRealLists();

                final Position newPosition = new Position(newMediator, newPossibleActionList);
                positionsMap.put(newPosition, answer);
            }
        }
        return positionsMap;
    }

    public Collection<Pair<Position, IAnswer>> getAll() {

        final Collection<Pair<Position, IAnswer>> positions = new ArrayList<>();
        final Collection<Figure> figures = mediator.getFigures(activeColor);

        for (final Figure figure : figures) {

            for (final IField finishField : possibleActionList.getRealList(figure)) {

                final IField startField = mediator.getField(figure);

                // определить тип хода
                final IMediator newMediator = new Mediator(mediator);
                final IStoryGame newStoryGame = new StoryGame((StoryGame) possibleActionList.getStoryGame());
                final PossibleActionList newPossibleActionList = new PossibleActionList(board, newMediator, newStoryGame);

                final AllowMoveCommand allowMoveCommand =
                        new AllowMoveCommand(newMediator, newPossibleActionList, board, newStoryGame);

                final IAllowCommand command = allowMoveCommand.getCommand(startField, finishField);

                final IAnswer answer = new AnswerSimbol(
                        startField.getXCoord(),
                        startField.getYCoord(),
                        finishField.getXCoord(),
                        finishField.getYCoord(),
                        'Q');

                // перейти к следующему ходу
                command.execute(answer);

                newStoryGame.update(figure);
                newPossibleActionList.updateRealLists();

                final Position newPosition = new Position(newMediator, newPossibleActionList);
                positions.add(new Pair<>(newPosition, answer));
            }
        }
        return positions;
    }

    @Override
    public boolean hasNext() {
        if (iterField.hasNext()) return true;
        if (!iterFigure.hasNext()) return false;

        while (!iterField.hasNext()) {
            if (!iterFigure.hasNext()) return false;
            currentFigure = iterFigure.next();
            iterField = possibleActionList.getRealList(currentFigure).iterator();
        }
        return true;
    }

    @Override
    public Pair<Position, IAnswer> next() {

        final IField finishField = iterField.next();
        final IField startField = mediator.getField(currentFigure);

        // определить тип хода
        final IMediator newMediator = new Mediator(mediator);
        final IStoryGame newStoryGame = new StoryGame((StoryGame) possibleActionList.getStoryGame());
        final PossibleActionList newPossibleActionList = new PossibleActionList(board, newMediator, newStoryGame);

        final AllowMoveCommand allowMoveCommand =
                new AllowMoveCommand(newMediator, newPossibleActionList, board, newStoryGame);

        final IAllowCommand command = allowMoveCommand.getCommand(startField, finishField);
        final IAnswer answer = new AnswerSimbol(
                startField.getXCoord(),
                startField.getYCoord(),
                finishField.getXCoord(),
                finishField.getYCoord(),
                'Q');

        command.execute(answer);

        newStoryGame.update(currentFigure);

        newPossibleActionList.updateRealLists();

        final Position newPosition = new Position(newMediator, newPossibleActionList);
        return new Pair(newPosition, answer);
    }

    public int getCount() {
        int result = 0;
        for (final Figure figure : mediator.getFigures(activeColor)) {
            result += possibleActionList.getRealList(figure).size();
        }
        return result;
    }
}
