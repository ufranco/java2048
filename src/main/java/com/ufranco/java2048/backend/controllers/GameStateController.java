package com.ufranco.java2048.backend.controllers;

import com.ufranco.java2048.backend.models.GameState;
import com.ufranco.java2048.backend.services.GameStateService;

public class GameStateController {

  GameStateService service;

  public GameState getGameStatus() {
    return service.getGameState();
  }

  public GameState manageGameStatus(GameState gameStatus) {
    return service.manageGameState(gameStatus);
  }
}
