package neointernship.chess.logger;

import neointernship.chess.game.gameplay.gamestate.controller.draw.IDrawController;
import neointernship.chess.game.gameplay.moveaction.commands.allow.IAllowCommand;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.field.IField;

public interface IGameLogger {
    void logStartGame(final IPlayer playerOne, final IPlayer playerTwo);
    void logPlayerMoveAction(final Color color, final Figure figure,
                             final IField startField, final IField finalField,final IAllowCommand command);
    void logPlayerWrongAction(final Color color, final IField field);
    void logPlayerWin(final Color color);
    void logStalemate();
    void logDraw(final IDrawController drawController );
}
