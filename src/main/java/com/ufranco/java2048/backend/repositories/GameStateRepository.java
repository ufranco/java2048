package com.ufranco.java2048.backend.repositories;

import com.ufranco.java2048.backend.models.GameState;

public class GameStateRepository {

  private GameState state;

  public GameState get() {
    return state;
  }

  public GameState create() {
    state = new GameState();
    return this.state;
  }

  public void update(GameState state) {
    this.state = state;
  }

  public GameState reset() {
    return create();
  }


}
