package neointernship.chess.logger;

import neointernship.chess.game.gameplay.gamestate.controller.draw.IDrawController;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.communication.message.ChessCodes;

public interface IGameLogger {
    void logStartGame(final String firstPlayerName, final String secondPlayerName);
    void logPlayerMoveAction(final Color color, final Figure figure,
                             final IField startField, final IField finalField, final ChessCodes chessCodes);
    void logPlayerWrongAction(final Color color, final IField field);
    void logPlayerWin(final Color color);
    void logStalemate();
    void logDraw(final IDrawController drawController );
}
