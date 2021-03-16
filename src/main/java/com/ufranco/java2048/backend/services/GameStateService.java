package com.ufranco.java2048.backend.services;

import com.ufranco.java2048.backend.models.GameState;
import com.ufranco.java2048.backend.repositories.GameStateRepository;

public class GameStateService {

  GameStateRepository repository;

  public GameState getGameState() {
    return repository.createGameState();
  }

  public GameState manageGameState(GameState gameState) {

    //

    return gameState;
  }

  public void gameOver() {

  }

}
