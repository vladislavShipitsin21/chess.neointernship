package neointernship.client;

import neointernship.client.controller.Controller;
import neointernship.client.view.View;


public class Client{
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
        final Runnable view = new View();
        new Thread(view).start();
    }

    private static void startController() {
        final Runnable controller = new Controller();
        new Thread(controller).start();
    }
}
