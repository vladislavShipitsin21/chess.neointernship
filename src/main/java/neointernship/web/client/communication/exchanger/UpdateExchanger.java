package neointernship.web.client.communication.exchanger;

import neointernship.web.client.communication.data.update.IUpdate;

import java.util.concurrent.Exchanger;

public class UpdateExchanger {
    private static final Exchanger<IUpdate> exchanger = new Exchanger<>();

    private UpdateExchanger(){}

    public static IUpdate exchange(final IUpdate update) throws InterruptedException {
        return exchanger.exchange(update);
    }
}
