package neointernship.web.client.communication.exchanger;

import neointernship.web.client.communication.data.initgame.IInitGame;

import java.util.concurrent.Exchanger;

public class InitGameExchanger {
    private static final Exchanger<IInitGame> exchanger = new Exchanger<>();

    private InitGameExchanger(){}

    public static IInitGame exchange(final IInitGame initGame) throws InterruptedException {
        return exchanger.exchange(initGame);
    }
}
