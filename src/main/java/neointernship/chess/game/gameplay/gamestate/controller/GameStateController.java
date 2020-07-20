package neointernship.chess.game.gameplay.gamestate.controller;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.gamestate.state.GameState;
import neointernship.chess.game.gameplay.gamestate.update.FiguresHaveMovesComputation;
import neointernship.chess.game.gameplay.gamestate.update.GameStateDefineLogic;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.enums.KingState;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.subscriber.ISubscriberKing;
import neointernship.chess.logger.IGameLogger;

public class GameStateController implements ISubscriberKing, IGameStateController {
    private GameState currentState;

    private final FiguresHaveMovesComputation figuresHaveMovesComputation;
    private final GameStateDefineLogic gameStateDefineLogic;
    private final IGameLogger gameLogger;

    public GameStateController(final IPossibleActionList possibleActionList,
                               final IMediator mediator,
                               final IGameLogger gameLogger) {

        currentState = new GameState(EnumGameState.ALIVE, Color.BOTH);
        figuresHaveMovesComputation = new FiguresHaveMovesComputation(possibleActionList, mediator);
        gameStateDefineLogic = new GameStateDefineLogic();
        this.gameLogger = gameLogger;
    }

    @Override
    public boolean isMatchAlive() {
        return currentState.getValue() == EnumGameState.ALIVE;
    }

    @Override
    public void showResults() {

        switch (currentState.getValue()){
            case MATE:
                switch (currentState.getColor()) {
                    case BLACK:
                        gameLogger.logPlayerWin(Color.WHITE);
                        break;
                    case WHITE:
                        gameLogger.logPlayerWin(Color.BLACK);
                        break;
                }
                break;
            case STALEMATE:
                gameLogger.logStalemate();
                break;
        }
    }

    @Override
    public void update(Color color, KingState kingState) {
        boolean figuresHaveMoves = figuresHaveMovesComputation.check(color);
        if(!figuresHaveMoves){
            System.out.println();
        }
        currentState = new GameState(gameStateDefineLogic.getState(kingState, figuresHaveMoves), color);

        System.out.format("Game status updated: %s\n", currentState.getValue());
    }
}

