package neointernship.bots.my_figureactions.patterns.real;

import neointernship.bots.my_figureactions.MyPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.patterns.real.IRealBasicPatterns;
import neointernship.chess.game.gameplay.moveaction.commands.allow.AllowMoveCommand;
import neointernship.chess.game.gameplay.moveaction.commands.allow.IAllowCommand;
import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.game.story.StoryGame;

import java.util.ArrayList;
import java.util.Collection;

public class MyRealBasicPatterns implements IRealBasicPatterns {
    private final IMediator mediator;
    private final IBoard board;
    private final IStoryGame storyGame;

    public MyRealBasicPatterns(final IMediator mediator,
                               final IBoard board,
                               final IStoryGame storyGame) {
        this.mediator = mediator;
        this.board = board;
        this.storyGame = storyGame;
    }

    @Override
    public Collection<IField> getRealMoveList(final Figure figure, final Collection<IField> potentialMoveList) {
        IAllowCommand command;
        final ArrayList<IField> realList = new ArrayList<>();
        final IField startField = mediator.getField(figure);
        final Color colorFigure = figure.getColor();
        final Color colorOpponent = Color.swapColor(colorFigure);

        for (final IField finishField : potentialMoveList) {

            final IMediator newMediator = new Mediator(mediator);
            final IStoryGame newStoryGame = new StoryGame((StoryGame) storyGame);
            final IPossibleActionList newPossibleActionList = new MyPossibleActionList(board, newMediator, newStoryGame);

            final AllowMoveCommand allowMoveCommand =
                    new AllowMoveCommand(newMediator, newPossibleActionList, board, newStoryGame);

            command = allowMoveCommand.getCommand(startField, finishField);
            final IAnswer answer = new Answer(
                    startField.getXCoord(),
                    startField.getYCoord(),
                    finishField.getXCoord(),
                    finishField.getYCoord(),
                    'Q');

            command.execute(answer);

            newPossibleActionList.updatePotentialLists(colorOpponent);

            if (command.isCorrect(colorFigure, newPossibleActionList)) {
                realList.add(finishField);
            }
        }
        return realList;
    }
}
