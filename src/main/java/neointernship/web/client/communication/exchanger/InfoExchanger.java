package neointernship.web.client.communication.exchanger;

import neointernship.web.client.communication.data.initinfo.IInitInfo;

import java.util.concurrent.Exchanger;

public class InfoExchanger {
    private static final Exchanger<IInitInfo> exchanger = new Exchanger<>();

    private InfoExchanger(){}

    public static IInitInfo exchange(final IInitInfo name) throws InterruptedException {
        return exchanger.exchange(name);
    }
}
