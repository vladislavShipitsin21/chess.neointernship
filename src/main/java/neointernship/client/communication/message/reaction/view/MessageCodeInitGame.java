package neointernship.client.communication.message.reaction.view;

import neointernship.client.communication.data.initgame.IInitGame;
import neointernship.client.communication.exchanger.ExchangerForInitGame;
import neointernship.client.communication.exchanger.ExchangerForMessage;
import neointernship.client.communication.message.IMessage;
import neointernship.client.communication.message.Message;
import neointernship.client.communication.message.MessageCode;
import neointernship.client.player.Bot;
import neointernship.client.player.IPlayer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;

public class MessageCodeInitGame implements IMessageCode {
    @Override
    public void execute(IPlayer player) throws InterruptedException {
        final IInitGame initGame = ExchangerForInitGame.exchange(null);
        final IMediator mediator = initGame.getMediator();
        final IBoard board = initGame.getBoard();
        final Color color = initGame.getColor();
        player = new Bot(mediator, board, color);
        final IMessage mes = new Message(MessageCode.OK);
        ExchangerForMessage.exchange(mes);
    }
}
