package neointernship.chess.logger;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.field.IField;

public interface IGameLogger {
    void logStartGame(final IPlayer playerOne, final IPlayer playerTwo);
    void logPlayerMoveAction(final IPlayer player, final Figure figure,
                                    final IField startField, final IField finalField);
    void logPlayerWrongAction(final IPlayer player, final IField field);
    void logPlayerWin(final Color color);
    void logStalemate();
}
