package neointernship.chess;

import neointernship.chess.game.selfplay.Selfplay;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Главный класс приложения.
 */
public class Main {

    public static BufferedReader in1; // поток чтения из сокета
    public static BufferedWriter out1; // поток завписи в сокет

    public static BufferedReader in2; // поток чтения из сокета
    public static BufferedWriter out2; // поток завписи в сокет

    /**
     * Главная функция, в которой мы инициализируем старт игры.
     *
     * @param args аргументы командной строки.
     */
    public static void main(final String[] args) {
        final ServerSocket server;
        Socket player1 = null;
        Socket player2 = null;

        try {
            server = new ServerSocket(8089);
            System.out.println("8081");
            player1 = server.accept();
            player2 = server.accept();
            in1 = new BufferedReader(new InputStreamReader(player1.getInputStream()));
            out1 = new BufferedWriter(new OutputStreamWriter(player1.getOutputStream()));
            in2 = new BufferedReader(new InputStreamReader(player2.getInputStream()));
            out2 = new BufferedWriter(new OutputStreamWriter(player2.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }



       /* final GameInitializer game = new GameInitializer();
        game.start();*/
        Selfplay selfplay = new Selfplay();
        selfplay.start();
    }
}