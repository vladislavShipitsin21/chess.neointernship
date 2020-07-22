package neointernship.client.player;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;

public interface IPlayer {
    IAnswer getAnswer();
    String getName();
    Color getColor();
    void setMediator(IMediator mediator);
    IMediator getMediator();
}
