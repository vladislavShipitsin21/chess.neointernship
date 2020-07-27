package neointernship.chess.game.gameplay.gamestate.controller;

import neointernship.chess.game.gameplay.gamestate.state.IGameState;

public interface IGameStateController {
    boolean isMatchAlive();
    IGameState getState();
}
