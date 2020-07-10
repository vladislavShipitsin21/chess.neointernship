package neointernship.chess.client.message;

public enum MessageCode {
    //Системные коды
    OK,
    ERROR,
    CONNECT,

    //Коды для view
    FIGURES_LIST,
    PAINT,

    //Коды для игрового процесса
    PICK_FIGURE,
    MOVE_FIGURE,

    //Коды окончания игры
    WIN,
    LOSE,
    DRAW
}
