package neointernship.web.client.communication.data.update;

import neointernship.chess.game.model.mediator.IMediator;

public class UpdateDto implements IUpdate {
    private IMediator mediator;

    public boolean validate() throws Exception {
        if (mediator == null) {
            throw new Exception("Отсутствует mediator");
        }
        return true;
    }

    @Override
    public IMediator getMediator() {
        return mediator;
    }
}
