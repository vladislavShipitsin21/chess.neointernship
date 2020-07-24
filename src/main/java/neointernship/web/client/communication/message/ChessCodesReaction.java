package neointernship.web.client.communication.message;

import neointernship.chess.game.gameplay.moveaction.commands.allow.*;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;

import java.util.HashMap;

public class ChessCodesReaction {
    private final HashMap<ChessCodes, IAllowCommand> chessCodesReaction;

    public ChessCodesReaction(final IBoard board, final IMediator mediator) {
        this.chessCodesReaction = new HashMap<>();
        initChessCodesReaction(chessCodesReaction, board, mediator);
    }

    private void initChessCodesReaction(final HashMap<ChessCodes, IAllowCommand> chessCodesReaction,
                                        final IBoard board,
                                        final IMediator mediator) {
        chessCodesReaction.put(ChessCodes.MOVE, new MoveCommand(board, mediator));
        chessCodesReaction.put(ChessCodes.ATTACK, new AttackCommand(board, mediator));
        chessCodesReaction.put(ChessCodes.CASTLING, new CastlingCommand(board, mediator));
        chessCodesReaction.put(ChessCodes.AISLE_TAKE, new AisleTakeCommand(board, mediator));
        chessCodesReaction.put(ChessCodes.TRANSFORMATION, new TransformationCommand(board, mediator));
    }

    public IAllowCommand get(final ChessCodes chessCodes){
        return chessCodesReaction.get(chessCodes);
    }
}
