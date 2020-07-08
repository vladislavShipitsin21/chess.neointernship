package neointernship.chess;

import neointernship.chess.game.gameplay.init.GameInitializer;

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

        final GameInitializer game = new GameInitializer();
       // game.start();
    }
}