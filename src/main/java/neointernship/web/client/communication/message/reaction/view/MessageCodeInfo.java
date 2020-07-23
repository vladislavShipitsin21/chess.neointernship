package neointernship.web.client.communication.message.reaction.view;

import neointernship.web.client.communication.data.info.IInfo;
import neointernship.web.client.communication.data.info.Info;
import neointernship.web.client.communication.exchanger.ExchangerForInfo;
import neointernship.web.client.player.Bot;
import neointernship.web.client.player.IPlayer;

public class MessageCodeInfo implements IMessageCode {
    @Override
    public void execute(IPlayer player) throws InterruptedException {
        player = new Bot();

        final IInfo name = new Info(player.getName(), player.getColor());
        ExchangerForInfo.exchange(name);
    }
}
