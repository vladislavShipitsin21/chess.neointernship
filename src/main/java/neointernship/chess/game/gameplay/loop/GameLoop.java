package neointernship.chess.game.gameplay.loop;

import neointernship.chess.game.console.ConsoleBoardWriter;
import neointernship.chess.game.console.IConsoleBoardWriter;
import neointernship.chess.game.gameplay.activecolorcontroller.IActiveColorController;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.gameprocesscontroller.GameProcessController;
import neointernship.chess.game.gameplay.gameprocesscontroller.IGameProcessController;
import neointernship.chess.game.gameplay.gamestate.controller.GameStateController;
import neointernship.chess.game.gameplay.gamestate.controller.IGameStateController;
import neointernship.chess.game.gameplay.gamestate.controller.draw.DrawController;
import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.gameplay.kingstate.controller.IKingStateController;
import neointernship.chess.game.gameplay.kingstate.controller.KingsStateController;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.subscriber.ISubscriber;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.logger.IGameLogger;

/**
 * Класс, реализующий основное ядро игры (игровой цикл)
 */
public class GameLoop implements IGameLoop {
    private final IActiveColorController activeColorController;
    private final IGameStateController gameStateController;
    private final IGameProcessController gameProcessController;
    private final IKingStateController kingStateController;
    private final IConsoleBoardWriter consoleBoardWriter;
    private final IGameLogger gameLogger;
    private final DrawController drawController; // todo сделать его gameStateController

//    сделать глобальный stateController который будет содержать в себе
//            контроллер короля и контроллер ничьи

    public GameLoop(final IMediator mediator,
                    final IPossibleActionList possibleActionList,
                    final IBoard board,
                    final IActiveColorController activeColorController,
                    final IGameLogger gameLogger,
                    final IStoryGame storyGame) {

        this.gameLogger = gameLogger;
        this.activeColorController = activeColorController;

        gameStateController = new GameStateController(possibleActionList, mediator, gameLogger);
        gameProcessController = new GameProcessController(mediator, possibleActionList, board,gameLogger,storyGame);
        kingStateController = new KingsStateController(possibleActionList, mediator, Color.WHITE);
        drawController = new DrawController(mediator,gameLogger,storyGame);

        consoleBoardWriter = new ConsoleBoardWriter(mediator, board);
        kingStateController.addToSubscriber((ISubscriber) gameStateController);

    }

    /**
     * Активация главного игрового цикла.
     */
    @Override
    public void doIteration(final IAnswer answer) {
        Color activeColor = activeColorController.getCurrentColor();

        do {
            gameProcessController.makeTurn(activeColor, answer, gameLogger);
        } while (!gameProcessController.playerDidMove());

        kingStateController.setActiveColor(activeColor);
        kingStateController.updateState();

        consoleBoardWriter.printBoard();
        gameStateController.showResults();
    }

    @Override
    public boolean isAlive() {
        return gameStateController.isMatchAlive() && !drawController.isDraw();
    }

    @Override
    public IGameState getMatchResult() {
        return gameStateController.getState();
    }

    @Override
    public void activate() {
        while (isAlive()){
            
        }
    }

}
