package neointernship.chess.game.model.playmap.board;

import neointernship.chess.game.model.playmap.field.IField;

public interface IBoard {
    IField getField(final int x, final int y);
    short getSize();
}
