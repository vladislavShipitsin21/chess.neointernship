package neointernship.chess.game.gameplay.init.getstartinfo;

import neointernship.chess.game.gameplay.message.IMessage;

import java.util.Scanner;

public class StartInfoGetter implements IStartInfoGetter {
    private final IMessage message;

    private String firstPlayerName;
    private String secondPlayerName;
    private String chessType;

    public StartInfoGetter(final IMessage message) {
        this.message = message;
    }

    @Override
    public void setStartInfo() {
        System.out.print("Type of chess (1 - classical; 2 - Fischer): ");
        chessType = message.getMessage();


        /*System.out.print("White side. Player 1 name: ");
        firstPlayerName = scanner.nextLine();
        System.out.print("White side. Player 1 name: ");
        secondPlayerName = scanner.nextLine();
        System.out.print("White side. Player 1 name: ");
        firstPlayerName = scanner.nextLine();*/
    }

    @Override
    public String getFirstPlayerName() {
        return firstPlayerName;
    }

    @Override
    public String getSecondPlayerName() {
        return secondPlayerName;
    }

    @Override
    public String getChessType() {
        return chessType;
    }
}