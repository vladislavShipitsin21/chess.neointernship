package neointernship.chess.game.gameplay.gamestate.controller;

import neointernship.chess.game.gameplay.gamestate.state.GameState;

public interface IGameStateController {
    GameState getState();
    boolean isMatchAlive();
}
