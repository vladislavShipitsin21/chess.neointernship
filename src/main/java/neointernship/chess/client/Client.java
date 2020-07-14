package neointernship.chess.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import neointernship.chess.client.controller.Controller;
import neointernship.chess.client.message.Message;
import neointernship.chess.client.view.View;

import java.util.concurrent.Exchanger;


public class Client{
    public static final Exchanger<Message> exchanger = new Exchanger<>();

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
