package com.ufranco.java2048.backend.services;

import com.ufranco.java2048.backend.models.GameState;
import com.ufranco.java2048.backend.repositories.GameStateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.ufranco.java2048.backend.utils.Movement.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameStateTest {

  @Mock
  GameStateRepository repository;

  @InjectMocks
  GameStateService service;

  @Test
  public void updateGameStateUpMovementTest() {
    when(repository.getGameState()).thenReturn(
      new GameState(
        new int[][]{
          new int[]{ 0, 0, 0, 0 },
          new int[]{ 2, 0, 4, 0 },
          new int[]{ 2, 2, 4, 4 },
          new int[]{ 0, 0, 2, 0 }
        },
        5
      )
    );

    GameState postManagedState = service.updateGameState(UP);

    Assertions.assertArrayEquals(
      postManagedState.getBoard(),
      new int[][]{
        new int[]{ 4, 2, 8, 4 },
        new int[]{ 0, 0, 2, 0 },
        new int[]{ 0, 0, 0, 0 },
        new int[]{ 0, 0, 0, 0 }
      }
    );

    Assertions.assertEquals(
      postManagedState.getMoves(),
      6
    );
  }

  @Test
  public void updateGameStateDownMovementTest() {
    when(repository.getGameState()).thenReturn(
      new GameState(
        new int[][]{
          new int[]{ 0, 0, 0, 0 },
          new int[]{ 2, 0, 4, 0 },
          new int[]{ 2, 2, 4, 4 },
          new int[]{ 0, 0, 2, 0 }
        },
        11
      )
    );

    GameState postManagedState = service.updateGameState(DOWN);

    Assertions.assertArrayEquals(
      postManagedState.getBoard(),
      new int[][]{
        new int[]{ 0, 0, 0, 0 },
        new int[]{ 0, 0, 0, 0 },
        new int[]{ 0, 0, 8, 0 },
        new int[]{ 4, 2, 2, 4 }
      }
    );

    Assertions.assertEquals(
      postManagedState.getMoves(),
      12
    );

  }

  @Test
  public void updateGameStateLeftMovementTest() {

    when(repository.getGameState()).thenReturn(
      new GameState(
        new int[][]{
          new int[]{ 0, 0, 0, 0 },
          new int[]{ 2, 0, 4, 0 },
          new int[]{ 2, 2, 4, 4 },
          new int[]{ 0, 0, 2, 0 }
        },
        8
      )
    );

    GameState postManagedState = service.updateGameState(LEFT);

    Assertions.assertArrayEquals(
      postManagedState.getBoard(),
      new int[][]{
        new int[]{ 0, 0, 0, 0 },
        new int[]{ 2, 4, 0, 0 },
        new int[]{ 4, 8, 0, 0 },
        new int[]{ 2, 0, 0, 0 }
      }
    );

    Assertions.assertEquals(
      postManagedState.getMoves(),
      9
    );

  }


  @Test
  public void updateGameStateRightMovementTest() {

    when(repository.getGameState()).thenReturn(
      new GameState(
        new int[][]{
          new int[]{ 0, 0, 0, 0 },
          new int[]{ 2, 0, 4, 0 },
          new int[]{ 2, 2, 4, 4 },
          new int[]{ 0, 0, 2, 0 }
        },
        17
      )
    );

    GameState postManagedState = service.updateGameState(RIGHT);

    Assertions.assertArrayEquals(
      postManagedState.getBoard(),
      new int[][]{
        new int[]{ 0, 0, 0, 0 },
        new int[]{ 0, 0, 2, 4 },
        new int[]{ 0, 0, 4, 8 },
        new int[]{ 0, 0, 0, 2 }
      }
    );

    Assertions.assertEquals(
      postManagedState.getMoves(),
      18
    );

  }
}
