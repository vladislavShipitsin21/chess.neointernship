package neointernship.chess.client;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Client extends Application{

    /**
     * Главная функция, начало работы клиента
     *
     * @param args аргументы командной строки
     */
    public static void main(final String[] args) {
        Application.launch(args);
    }

    /**
     * Начало отрисовки gui
     * Вызывается при выполнении Application.launch(args)
     *
     * @param stage полотно для отрисовки, используется автоматически
     */
    public void start(final Stage stage) {
        final float boardSize = 800;
        final float sizeKoef = boardSize / 1597;
        final float fieldSize = 178 * sizeKoef;
        final float paddingSize = 80 * sizeKoef;

        final ImageView imageView = new ImageView("testBoard.jpg");
        imageView.setFitHeight(boardSize);
        imageView.setFitWidth(boardSize);

        imageView.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED,
                new EventHandler<javafx.scene.input.MouseEvent>() {

                    /**
                     * Обработка события клика мыши
                     *
                     * @param event событие клик
                     */
            public void handle(final javafx.scene.input.MouseEvent event) {
                final double X = (event.getX() - paddingSize) / fieldSize;
                final double Y = 8 - (event.getY() - paddingSize) / fieldSize;
                if (X >= 0 && X < 8 && Y >= 0 && Y < 8) {
                    System.out.println((int)X + " " + (int)Y);
                }
            }
        });

        stage.setScene(new Scene(new Pane(imageView)));
        stage.setResizable(false);
        stage.show();
    }
}
