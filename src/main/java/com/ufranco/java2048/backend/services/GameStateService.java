package com.ufranco.java2048.backend.services;

import com.ufranco.java2048.backend.models.GameState;
import com.ufranco.java2048.backend.repositories.GameStateRepository;
import com.ufranco.java2048.backend.utils.Movement;

public class GameStateService {

  GameStateRepository repository;

  public GameState getGameState() {
    return repository.createGameState();
  }

  public GameState updateGameState(Movement movement) {
    return null;
  }

  public boolean isGameOver(int[][] board) {
    return false;
  }

  public int[][] insertValueRandomInFreePosition(int[][] dummyBoard) {

    return null;
  }
}
