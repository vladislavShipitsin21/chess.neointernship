package neointernship.chess.client.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static neointernship.chess.client.Client.exchanger;

public class View extends Application implements Runnable{
    @Override
    public void run() {
        Application.launch();
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
                new EventHandler<MouseEvent>() {

                    /**
                     * Обработка события клика мыши
                     *
                     * @param event событие клик
                     */
                    public void handle(final javafx.scene.input.MouseEvent event) {
                        final double X = (event.getX() - paddingSize) / fieldSize;
                        final double Y = 8 - (event.getY() - paddingSize) / fieldSize;
                        if (X >= 0 && X < 8 && Y >= 0 && Y < 8) {
                            try {
                                exchanger.exchange(new Integer[]{(int)X, (int)Y});
                            } catch (final InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

        stage.setScene(new Scene(new Pane(imageView)));
        stage.setResizable(false);
        stage.show();
    }
}
