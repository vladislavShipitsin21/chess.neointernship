package neointernship;

import java.time.LocalTime;
import java.util.ArrayList;

public class MoreBots {

    public static void main(String[] args) throws InterruptedException {

        int startTime = LocalTime.now().getSecond();
        System.out.println("start :" + startTime);

        final int countBot = 10;

        ArrayList<ControllerBot> controllerBots = new ArrayList<>();
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < countBot; i++) {
            controllerBots.add(new ControllerBot(i));
        }
        for (ControllerBot controllerBot : controllerBots) {
            threads.add(new Thread(controllerBot));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        int finishTime = LocalTime.now().getSecond();
        System.out.println("finish :" + finishTime);

        System.out.println(finishTime - startTime);
    }

}
