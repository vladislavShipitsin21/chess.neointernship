package neointernship.web.client.communication.message;

public enum MessageCode {
    //Системные коды
    OK,
    ERROR_TURN,
    CONNECT,

    //Коды для view
    INIT_GAME,
    UPDATE,

    //Коды для игрового процесса
    PICK_FIGURE,
    MOVE_FIGURE,
    TURN,

    //Коды окончания игры
    WIN,
    LOSE,
    DRAW
}
