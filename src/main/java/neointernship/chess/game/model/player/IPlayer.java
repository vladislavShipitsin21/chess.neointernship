package neointernship.chess.game.model.player;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

public interface IPlayer {
    IAnswer getAnswer(final IBoard board, final IMediator mediator, final IPossibleActionList list);
    Color getColor();
    String getName();
    BufferedReader getReader(); // поток завписи в сокет
    BufferedWriter getWriter();
    void setName(final String name);
    Socket getSocket();
}