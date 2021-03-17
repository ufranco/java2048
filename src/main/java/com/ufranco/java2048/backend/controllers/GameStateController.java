package com.ufranco.java2048.backend.controllers;

import com.ufranco.java2048.backend.models.GameState;
import com.ufranco.java2048.backend.services.GameStateService;
import com.ufranco.java2048.backend.utils.Movement;

public class GameStateController {

  GameStateService service;

  public GameState getGameState() {
    return service.getGameState();
  }

  public GameState updateGameState(Movement movement) {
    return service.updateGameState(movement);
  }
}
