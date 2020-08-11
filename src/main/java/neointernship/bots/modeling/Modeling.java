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
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.game.story.StoryGame;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class Modeling implements Iterator<Map.Entry<Position,IAnswer>> {

    private static final IBoard board = new Board();

    private final Position actualPosition;
    private final Color activeColor;

    private final IMediator mediator;
    private final PossibleActionList possibleActionList;

    private final Iterator<Figure> iterFigure;
    private Figure currentFigure;
    private Iterator<IField> iterField;

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

    @Override
    public boolean hasNext() {
        if(iterField.hasNext()) return true;
        if(!iterFigure.hasNext()) return false;

        while(!iterField.hasNext()){
            if(!iterFigure.hasNext()) return false;
            currentFigure = iterFigure.next();
            iterField = possibleActionList.getRealList(currentFigure).iterator();
        }
        return true;
    }

    @Override
    public Map.Entry<Position,IAnswer> next() {

        IField finishField = iterField.next();
        IField startField = mediator.getField(currentFigure);

        // определить тип хода
        IMediator newMediator = new Mediator(mediator);
        IStoryGame newStoryGame = new StoryGame((StoryGame) possibleActionList.getStoryGame());
        PossibleActionList newPossibleActionList = new PossibleActionList(new Board(), newMediator, newStoryGame);

        AllowMoveCommand allowMoveCommand =
                new AllowMoveCommand(newMediator, newPossibleActionList, board, newStoryGame);

        IAllowCommand command = allowMoveCommand.getCommand(startField, finishField);
        IAnswer answer = new AnswerSimbol(
                startField.getXCoord(),
                startField.getYCoord(),
                finishField.getXCoord(),
                finishField.getYCoord(),
                'Q');

        command.execute(answer); // todo если превращение то выдывать другие варианты

        newStoryGame.update(currentFigure);
        newPossibleActionList.updateRealLists();

        Position newPosition = new Position(newMediator, newPossibleActionList);
        return new Map.Entry<Position, IAnswer>() {
            @Override
            public Position getKey() {
                return newPosition;
            }

            @Override
            public IAnswer getValue() {
                return answer;
            }

            @Override
            public IAnswer setValue(IAnswer value) {
                return null;
            }
        };
    }
}
