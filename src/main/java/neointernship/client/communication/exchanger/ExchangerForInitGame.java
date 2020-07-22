package neointernship.client.communication.exchanger;

import neointernship.client.communication.data.initgame.IInitGame;

import java.util.concurrent.Exchanger;

public class ExchangerForInitGame {
    private static final Exchanger<IInitGame> exchanger = new Exchanger<>();

    private ExchangerForInitGame(){}

    public static IInitGame exchange(final IInitGame initGame) throws InterruptedException {
        return exchanger.exchange(initGame);
    }
}
