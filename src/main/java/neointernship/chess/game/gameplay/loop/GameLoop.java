package neointernship.chess.game.gameplay.loop;

import neointernship.chess.game.console.ConsoleBoardWriter;
import neointernship.chess.game.console.IConsoleBoardWriter;
import neointernship.chess.game.gameplay.activeplayercontroller.ActivePlayerController;
import neointernship.chess.game.gameplay.activeplayercontroller.IActivePlayerController;
import neointernship.chess.game.gameplay.gameprocesscontroller.GameProcessController;
import neointernship.chess.game.gameplay.gameprocesscontroller.IGameProcessController;
import neointernship.chess.game.gameplay.gamestate.controller.GameStateController;
import neointernship.chess.game.gameplay.gamestate.controller.IGameStateController;
import neointernship.chess.game.gameplay.kingstate.controller.IKingStateController;
import neointernship.chess.game.gameplay.kingstate.controller.KingsStateController;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.model.subscriber.ISubscriber;

/**
 * Класс, реализующий основное ядро игры (игровой цикл)
 */
public class GameLoop implements IGameLoop {
    private final IMediator mediator;
    private final IPossibleActionList possibleActionList;
    private final IBoard board;
    private IPlayer activePlayer;

    private final IActivePlayerController activePlayerController;
    private final IGameStateController gameStateController;
    private final IGameProcessController gameProcessController;
    private final IKingStateController kingStateController;

    private final IConsoleBoardWriter consoleBoardWriter;

    public GameLoop(final IMediator mediator,
                    final IPossibleActionList possibleActionList,
                    final IBoard board,
                    final IPlayer firstPlayer,
                    final IPlayer secondPlayer) {
        this.activePlayer = firstPlayer;

        this.mediator = mediator;
        this.possibleActionList = possibleActionList;
        this.board = board;

        activePlayerController = new ActivePlayerController(firstPlayer, secondPlayer);
        gameStateController = new GameStateController(possibleActionList, mediator);
        gameProcessController = new GameProcessController(mediator, possibleActionList, board);
        kingStateController = new KingsStateController(possibleActionList, mediator, activePlayer);

        consoleBoardWriter = new ConsoleBoardWriter(mediator, board);
    }

    /**
     * Активация главного игрового цикла.
     */
    @Override
    public void activate() {
        kingStateController.addToSubscriber((ISubscriber) gameStateController);

        int countAnswer = 0;

        while (gameStateController.isMatchAlive() && countAnswer < 50) {
            do {
                consoleBoardWriter.printBoard();
                IAnswer answer = activePlayer.getAnswer(board, mediator, possibleActionList);
                gameProcessController.makeTurn(activePlayer.getColor(), answer);

            } while (!gameProcessController.playerDidMove());

            activePlayer = activePlayerController.getNextPlayer();
            showAvailableMoves();
            kingStateController.updateState();
            consoleBoardWriter.printMatchResult(gameStateController.getState());

            countAnswer++;
        }
    }

    private void showAvailableMoves() {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                IField field = board.getField(i, j);
                Figure figure = mediator.getFigure(field);
                if (figure != null) {
                    System.out.println(figure.getName() + " " + figure.getColor());
                    for (IField field1 : possibleActionList.getList(figure)) {
                        System.out.println(field1.getXCoord() + " " + field1.getYCoord());
                    }
                    System.out.print("\n");
                }
            }
        }
    }
}
