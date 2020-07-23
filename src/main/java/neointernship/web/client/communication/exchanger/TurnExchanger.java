package neointernship.web.client.communication.exchanger;

import neointernship.web.client.communication.data.turn.ITurn;

import java.util.concurrent.Exchanger;

public class TurnExchanger {
    private static final Exchanger<ITurn> exchanger = new Exchanger<>();

    private TurnExchanger(){}

    public static ITurn exchange(final ITurn turn) throws InterruptedException {
        return exchanger.exchange(turn);
    }
}
