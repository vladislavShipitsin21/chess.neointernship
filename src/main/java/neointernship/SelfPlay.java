package neointernship;

import neointernship.chess.game.model.enums.Color;
import neointernship.web.client.controller.ControllerBot;
import neointernship.web.client.player.APlayer;
import neointernship.web.client.player.ExpectiMaxBot;
import neointernship.web.client.player.MiniMaxBot;
import neointernship.web.client.player.RandomBot;


import java.time.LocalTime;
import java.util.Scanner;

public class SelfPlay {

    public static void main(final String[] args) throws InterruptedException {

        final Scanner scanner = new Scanner(System.in);

        final APlayer player1 = getPlayer(scanner,Color.WHITE);
        final APlayer player2 = getPlayer(scanner,Color.BLACK);

        final int countGame = getCountGame(scanner);

        for (int i = 0; i < countGame; i++) {

            final int timeStartM = LocalTime.now().getMinute();
            final int timeStartS = LocalTime.now().getSecond();
            System.out.println("start : " + i);

            final ControllerBot bot1 = new ControllerBot(player1);
            final ControllerBot bot2 = new ControllerBot(player2);

            final Thread thread1 = new Thread(bot1);
            final Thread thread2 = new Thread(bot2);

            thread1.setName("thread-bot1");
            thread2.setName("thread-bot2");

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

            final int timeFinishM = LocalTime.now().getMinute();
            final int timeFinishS = LocalTime.now().getSecond();

            System.out.println(timeFinishM - timeStartM + "min");
            System.out.println(timeFinishS - timeStartS + "cek");
            System.out.println("finish : " + i);
        }
    }

    private static APlayer getPlayer(final Scanner scanner,final Color color){
        System.out.println("Enter bot : ");
        System.out.println("1) Mini_max ");
        System.out.println("2) Expectimax");
        System.out.println("3) Random");

        final int type = scanner.nextInt();

        switch (type){
            case 1 : return new MiniMaxBot(color,2);
            case 2 : return new ExpectiMaxBot(color);
            case 3 : return new RandomBot(color);
        }
        System.out.println("error type");
        return null;
    }

    private static int getCountGame(final Scanner scanner){
        System.out.println("Enter count game : ");
        return scanner.nextInt();
    }
}
