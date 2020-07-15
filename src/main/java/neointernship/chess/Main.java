package neointernship.chess;

import neointernship.chess.game.gameplay.init.GameInitializer;
import neointernship.chess.game.selfplay.Selfplay;

/**
 * Главный класс приложения.
 */
public class Main {
    /**
     * Главная функция, в которой мы инициализируем старт игры.
     *
     * @param args аргументы командной строки.
     */
    public static void main(final String[] args) {

        final GameInitializer game = new GameInitializer(0);
        game.start();
        /*Selfplay selfplay = new Selfplay();
        selfplay.start();*/
    }
}