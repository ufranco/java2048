package com.ufranco.java2048.backend.services;

import com.ufranco.java2048.backend.repositories.ScoreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GameStateTest {

  @Mock
  ScoreRepository repository;

  @InjectMocks
  GameStateService service;

  @Test
  public void updateBoardUpTest() {

  }

  @Test
  public void updateBoardDownTest() {

  }

  @Test
  public void updateBoardLeftTest() {

  }

  @Test
  public void updateBoardRightTest() {

  }

}
