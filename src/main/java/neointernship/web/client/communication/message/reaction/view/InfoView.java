package neointernship.web.client.communication.message.reaction.view;

import neointernship.web.client.communication.data.initinfo.IInitInfo;
import neointernship.web.client.communication.data.initinfo.InitInfo;
import neointernship.web.client.communication.exchanger.InfoExchanger;
import neointernship.web.client.player.Bot;
import neointernship.web.client.player.IPlayer;

public class InfoView implements IMessageCodeView {
    @Override
    public void execute(IPlayer player) throws InterruptedException {
        player = new Bot();

        final IInitInfo name = new InitInfo(player.getName(), player.getColor());
        InfoExchanger.exchange(name);
    }
}
