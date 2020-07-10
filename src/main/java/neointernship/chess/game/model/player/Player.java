package neointernship.chess.game.model.player;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.board.IBoard;

public class Player implements IPlayer {
    private final Color color;
    private final String name;

    public Player(final String name, final Color color) {
        this.color = color;
        this.name = name;
    }

    @Override
    public IAnswer getAnswer(IBoard board, IMediator mediator, IPossibleActionList list) {
        return null;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
