package neointernship.bots;

import neointernship.chess.game.model.enums.ChessType;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.factory.Factory;
import neointernship.chess.game.model.figure.factory.IFactory;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.board.figuresstartposition.FiguresStartPositionRepository;
import neointernship.chess.game.model.playmap.field.IField;

public class InitGameMap {
    public static IMediator initGameMap() {
        final IBoard board = new Board();
        final FiguresStartPositionRepository figuresStartPositionRepository = new FiguresStartPositionRepository();
        final ChessType chessType = ChessType.CLASSIC;
        final Character FIELD_CHAR_EMPTY = '.';
        final IFactory figureFactory = new Factory();
        final IMediator mediator = new Mediator();

        final Character[][] figuresRepository = figuresStartPositionRepository.getStartPosition(chessType);
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                final IField field = board.getField(i, j);

                final Character currentChar = figuresRepository[i][j];
                if (currentChar != FIELD_CHAR_EMPTY) {
                    final Color color = i > 4 ? Color.WHITE : Color.BLACK;
                    final Figure figure = figureFactory.createFigure(currentChar, color);

                    mediator.addNewConnection(field, figure);
                }
            }
        }
        return mediator;
    }
}
