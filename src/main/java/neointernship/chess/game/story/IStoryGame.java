package neointernship.chess.game.story;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.playmap.field.IField;

public interface IStoryGame {
    /**
     * Возвращает true если данной фигурой совершали ход
     * @param figure
     * @return
     */
    boolean isMove(final Figure figure);

    /**
     * Возвращает фигуру, которой последний раз был совершён ход
     * @return
     */
    Figure getLastFigureMove();

    /**
     * Возвращает поле, на котором стояла фигура перед последним её ходом.
     * Если она не ходила, то её текущее положение
     * @param figure
     * @return
     */
    IField getLastFieldFigure(final Figure figure);

    /**
     *
     */
    void update(final Figure figure);

}
