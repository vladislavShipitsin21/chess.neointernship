package neointernship;

import neointernship.chess.game.model.enums.Color;
import neointernship.web.client.GUI.Input.InputVoid;
import neointernship.web.client.controller.ControllerBot;
import neointernship.web.client.player.ExpectiMaxBot;
import neointernship.web.client.player.RandomBot;

import java.time.LocalTime;

public class SelfPlay {

    public static void main(final String[] args) throws InterruptedException {

        final int countBot = 2;
        final int countGame = 1;

        for (int i = 0; i < countGame; i++) {

            final int timeStartM = LocalTime.now().getMinute();
            final int timeStartS = LocalTime.now().getSecond();
            System.out.println("start : " + i);

            final ControllerBot expectiMax = new ControllerBot(new ExpectiMaxBot(Color.WHITE));
            final ControllerBot miniMaxBot = new ControllerBot(new RandomBot(Color.BLACK));

            final Thread thread1 = new Thread(expectiMax);
            final Thread thread2 = new Thread(miniMaxBot);

            thread1.setName("thread-random");
            thread2.setName("thread-miniMax");

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
}
