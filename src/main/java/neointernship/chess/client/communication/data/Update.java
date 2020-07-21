package neointernship.chess.client.communication.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import neointernship.chess.game.model.mediator.IMediator;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Update {
    private final IMediator mediator;

    public Update(final IMediator mediator) {
        this.mediator = mediator;
    }

    public IMediator getMediator() {
        return mediator;
    }
}
