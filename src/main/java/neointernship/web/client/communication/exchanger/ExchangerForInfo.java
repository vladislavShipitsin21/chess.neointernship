package neointernship.web.client.communication.exchanger;

import neointernship.web.client.communication.data.info.IInfo;

import java.util.concurrent.Exchanger;

public class ExchangerForInfo {
    private static final Exchanger<IInfo> exchanger = new Exchanger<>();

    private ExchangerForInfo(){}

    public static IInfo exchange(final IInfo name) throws InterruptedException {
        return exchanger.exchange(name);
    }
}
