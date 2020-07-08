package neointernship.chess.game.model.playmap.board.figuresstartposition;

import neointernship.chess.game.model.enums.ChessTypes;

import java.util.HashMap;

public class FiguresStartPositionRepository {
    private final HashMap<ChessTypes, String> figuresPositionMap;

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
        figuresPositionMap = new HashMap<ChessTypes, String>() {
            {
                put(ChessTypes.CLASSIC, CLASSIC_CHESS_FIGURES_POSITION);
                put(ChessTypes.FISCHER, null);
            }
        };
    }

    public Character[][] getStartPosition(final ChessTypes chessType) {
        return figuresPositionMap.get(chessType);
    }
}
