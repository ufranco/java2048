package com.ufranco.java2048.backend.controllers;

import com.ufranco.java2048.backend.services.GameStateService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class GameStateControllerTest {

  @Mock
  GameStateService service;

  @InjectMocks
  GameStateController controller;
}
