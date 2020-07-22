package neointernship.web.client.communication.data.update;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import neointernship.chess.game.model.mediator.IMediator;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Update implements IUpdate {
    private final IMediator mediator;

    public Update(@JsonProperty("mediator") final IMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public IMediator getMediator() {
        return mediator;
    }
}
