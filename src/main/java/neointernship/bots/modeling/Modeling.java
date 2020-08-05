package neointernship.bots.modeling;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.gameplay.moveaction.commands.allow.AllowMoveCommand;
import neointernship.chess.game.gameplay.moveaction.commands.allow.IAllowCommand;
import neointernship.chess.game.model.answer.Answer;
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
import java.util.HashMap;
import java.util.Map;

public class Modeling {

    private static final IBoard board = new Board();

    // не возвращает одинаковые позиции
    public static Map<Position,IAnswer> modeling(final Position actualPosition,
                                                final Color activeColor){
        final Map<Position,IAnswer> positions = new HashMap<>();
        final Collection<Figure> figures = actualPosition.getMediator().getFigures(activeColor);
        final IMediator mediator = actualPosition.getMediator();
        final PossibleActionList possibleActionList = actualPosition.getPossibleActionList();

        for(final Figure figure : figures){
            for(final IField finishField : possibleActionList.getRealList(figure)){

                IField startField = actualPosition.getMediator().getField(figure);

                // определить тип хода
                IMediator newMediator = new Mediator(mediator);
                IStoryGame newStoryGame = new StoryGame((StoryGame)possibleActionList.getStoryGame());
                IPossibleActionList newPossibleActionList = new PossibleActionList(new Board(),newMediator,newStoryGame);

                AllowMoveCommand allowMoveCommand =
                        new AllowMoveCommand(newMediator, newPossibleActionList, board, newStoryGame);

                IAllowCommand command = allowMoveCommand.getCommand(startField, finishField);
                IAnswer answer = new Answer(
                        startField.getXCoord(),
                        startField.getYCoord(),
                        finishField.getXCoord(),
                        finishField.getYCoord(),
                        'Q');

                command.execute(answer);
                // перейти к следующему ходу
                newPossibleActionList.updateRealLists();
                Position newPosition = new Position(newMediator,newPossibleActionList);
                positions.put(newPosition,answer);
            }
        }
        return positions;
    }

}
