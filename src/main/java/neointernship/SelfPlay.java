package neointernship;

import neointernship.web.client.controller.ControllerFirstBot;
import neointernship.web.client.controller.ControllerRandomBot;

import java.time.LocalTime;

public class SelfPlay {

    public static void main(String[] args) throws InterruptedException {

        final int countBot = 2;
        final int countGame = 1;

        for (int i = 0; i < countGame ; i++) {
            int timeStartM = LocalTime.now().getMinute();
            int timeStartS = LocalTime.now().getSecond();
            System.out.println("start : " + i);

            ControllerRandomBot controllerRandomBots = new ControllerRandomBot(1);
            ControllerFirstBot controllerFirstBot = new ControllerFirstBot(0);

            Thread thread1 = new Thread(controllerRandomBots);
            Thread thread2 = new Thread(controllerFirstBot);

            thread1.setName("thread-random");
            thread2.setName("thread-miniMax");

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

            int timeFinishM = LocalTime.now().getMinute();
            int timeFinishS = LocalTime.now().getSecond();

            System.out.println(timeFinishM - timeStartM + "min");
            System.out.println(timeFinishS - timeStartS + "cek");
            System.out.println("finish : " + i);
        }

    }
}
