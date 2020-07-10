package neointernship.chess.client;

import neointernship.chess.client.controller.Controller;
import neointernship.chess.client.view.View;

import java.util.concurrent.Exchanger;


public class Client{
    public static Exchanger<Integer[]> exchanger = new Exchanger<>();

    /**
     * Главная функция, начало работы клиента
     *
     * @param args аргументы командной строки
     */
    public static void main(final String[] args) {
        startView();
        startController();
    }

    private static void startView() {
        final View view = new View();
        new Thread(view).start();
    }

    private static void startController() {
        final Controller controller = new Controller();
        new Thread(controller).start();
    }
}
