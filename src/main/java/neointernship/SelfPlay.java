package neointernship;

import neointernship.web.client.controller.ControllerFirstBot;
import neointernship.web.client.controller.ControllerRandomBot;

import java.time.LocalTime;
import java.util.ArrayList;

public class SelfPlay {

    public static void main(String[] args) throws InterruptedException {

        final int countBot = 2;
        final int countGame = 1;



        for (int i = 0; i < countGame ; i++) {

            ControllerRandomBot controllerRandomBots = new ControllerRandomBot(0);
            ControllerFirstBot controllerFirstBot = new ControllerFirstBot(0);

            Thread thread1 = new Thread(controllerRandomBots);
            Thread thread2 = new Thread(controllerFirstBot);

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();
        }
    }
}
