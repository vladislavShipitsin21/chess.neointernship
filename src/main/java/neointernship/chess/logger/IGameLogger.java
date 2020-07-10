package neointernship.chess.logger;

import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.field.IField;

public interface IGameLogger {
    public void logStartGame();
    public void logPlayerMoveAction(final IPlayer player, final Figure figure,
                                    final IField startField, final IField finalField);
    public void logPlayerWrongAction(final IPlayer player, final Figure figure, final IField field);
    public void logPlayerWrongField(final IPlayer player, final IField field);
}
