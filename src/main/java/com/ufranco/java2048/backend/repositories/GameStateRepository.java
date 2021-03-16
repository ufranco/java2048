package com.ufranco.java2048.backend.repositories;

import com.ufranco.java2048.backend.models.GameState;

public class GameStateRepository {

  private GameState state;

  public GameState getGameState() {
    return state;
  }

  public GameState createGameState() {
    state = new GameState();
    return this.state;
  }

  public GameState updateGameState(GameState state) {
    this.state = state;
    return this.state;
  }

  public GameState resetGameState() {
    return createGameState();
  }


}
