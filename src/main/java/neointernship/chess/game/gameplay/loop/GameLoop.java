package neointernship.chess.game.gameplay.loop;

import neointernship.chess.game.console.ConsoleBoardWriter;
import neointernship.chess.game.console.IConsoleBoardWriter;
import neointernship.chess.game.gameplay.activecolorcontroller.IActiveColorController;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.gameprocesscontroller.GameProcessController;
import neointernship.chess.game.gameplay.gameprocesscontroller.IGameProcessController;
import neointernship.chess.game.gameplay.gamestate.controller.GameStateController;
import neointernship.chess.game.gameplay.gamestate.controller.IGameStateController;
import neointernship.chess.game.gameplay.gamestate.controller.draw.DrawStateController;
import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.gameplay.kingstate.controller.IKingStateController;
import neointernship.chess.game.gameplay.kingstate.controller.KingsStateController;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.subscriber.ISubscriber;
import neointernship.chess.game.story.IStoryGame;
import neointernship.web.client.communication.message.ChessCodes;

import java.util.Collection;
import java.util.HashSet;

/**
 * Класс, реализующий основное ядро игры (игровой цикл)
 */
public class GameLoop implements IGameLoop {
    private final IActiveColorController activeColorController;

    private final IGameStateController gameStateController;
    private final Collection<IGameStateController> gameControllers;

    private final IGameProcessController gameProcessController;
    private final IKingStateController kingStateController;
    private final IConsoleBoardWriter consoleBoardWriter;

    private Color activeColor;
    private IGameState actualGameState;

    public GameLoop(final IMediator mediator,
                    final IPossibleActionList possibleActionList,
                    final IBoard board,
                    final IActiveColorController activeColorController,
                    final IStoryGame storyGame) {

        this.activeColorController = activeColorController;

        gameStateController = new GameStateController(possibleActionList, mediator);
        gameControllers = new HashSet<>();
        gameControllers.add(gameStateController);
        gameControllers.add(new DrawStateController(mediator,storyGame));
        actualGameState = gameStateController.getState();


        gameProcessController = new GameProcessController(mediator, possibleActionList, board,storyGame);
        kingStateController = new KingsStateController(possibleActionList, mediator, Color.WHITE);

        consoleBoardWriter = new ConsoleBoardWriter(mediator, board);
        kingStateController.addToSubscriber((ISubscriber) gameStateController);
    }

    /**
     * Активация главного игрового цикла.
     */
    @Override
    public ChessCodes doIteration(final IAnswer answer) {
        activeColor = activeColorController.getCurrentColor();

        gameProcessController.makeTurn(activeColor, answer);

        final ChessCodes chessCodes = gameProcessController.getChessCode();

        if(chessCodes != ChessCodes.ERROR) {
            activeColorController.update();
            activeColor = activeColorController.getCurrentColor();

            kingStateController.setActiveColor(activeColor);
            kingStateController.updateState();
            consoleBoardWriter.printBoard();
        }
        return chessCodes;
    }

    @Override
    public boolean isAlive() {
        for(IGameStateController controller : gameControllers){
            if(!controller.isMatchAlive()){
                actualGameState = controller.getState();
                return false;
            }
        }
        return true;
    }

    @Override
    public IGameState getMatchResult() {
        return actualGameState;
    }
}
