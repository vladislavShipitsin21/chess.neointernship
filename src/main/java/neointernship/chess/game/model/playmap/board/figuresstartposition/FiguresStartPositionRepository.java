package neointernship.chess.game.model.playmap.board.figuresstartposition;

import neointernship.chess.game.model.enums.ChessType;

import java.util.HashMap;

public class FiguresStartPositionRepository {
    private final HashMap<ChessType, String> figuresPositionMap;

    private static final String CLASSIC_CHESS_FIGURES_POSITION = "" +
            "RNBQKBNR" +
            "PPPPPPPP" +
            "********" +
            "********" +
            "********" +
            "********" +
            "PPPPPPPP" +
            "RNBKQBNR";

    private static final String FISCHER_CHESS_FIGURES_POSITION = ""; // TODO


    public FiguresStartPositionRepository() {
        figuresPositionMap = new HashMap<ChessType, String>() {
            {
                put(ChessType.CLASSIC, CLASSIC_CHESS_FIGURES_POSITION);
                put(ChessType.FISCHER, FISCHER_CHESS_FIGURES_POSITION);
            }
        };
    }

    public Character[][] getStartPosition(final ChessType chessType) {
        return figuresPositionMap.get(chessType);
    }
}
