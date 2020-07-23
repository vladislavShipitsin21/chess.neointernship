package neointernship.web.client.communication.message;

public enum ClientCodes {
    OK,
    INIT_INFO,
    TURN,

    //Коды для view
    INIT_GAME,
    UPDATE,

    //Коды для игрового процесса
    PICK_FIGURE,
    MOVE_FIGURE,

    //Коды окончания игры
    WIN,
    LOSE,
    DRAW
}
