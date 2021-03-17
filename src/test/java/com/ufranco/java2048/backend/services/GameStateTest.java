package com.ufranco.java2048.backend.services;

import com.ufranco.java2048.backend.models.GameState;
import com.ufranco.java2048.backend.repositories.ScoreRepository;
import com.ufranco.java2048.backend.utils.Movement;
import org.junit.jupiter.api.Assertions;
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

    GameState preManagedState = new GameState(
      new int[][]{
        new int[]{0, 0, 0, 0},
        new int[]{2, 0, 4, 0},
        new int[]{2, 2, 4, 4},
        new int[]{0, 0, 2, 0}
      },
      5,
      Movement.UP
    );
    GameState postManagedState = service.manageGameState(preManagedState);

    Assertions.assertArrayEquals(
      postManagedState.getBoard(),
      new int[][]{
        new int[]{4, 2, 8, 4},
        new int[]{0, 0, 2, 0},
        new int[]{0, 0, 0, 0},
        new int[]{0, 0, 0, 0}
      }
    );

    Assertions.assertEquals(
      postManagedState.getMoves(),
      preManagedState.getMoves() + 1
    );
  }

  @Test
  public void updateBoardDownTest() {
    GameState preManagedState = new GameState(
      new int[][]{

        new int[]{0, 0, 0, 0},
        new int[]{2, 0, 4, 0},
        new int[]{2, 2, 4, 4},
        new int[]{0, 0, 2, 0}
      },
      5,
      Movement.DOWN
    );

    GameState postManagedState = service.manageGameState(preManagedState);

    Assertions.assertArrayEquals(
      postManagedState.getBoard(),
      new int[][]{
        new int[]{0, 0, 0, 0},
        new int[]{0, 0, 0, 0},
        new int[]{0, 0, 8, 0},
        new int[]{4, 2, 2, 4}
      }
    );

    Assertions.assertEquals(
      postManagedState.getMoves(),
      preManagedState.getMoves() + 1
    );

  }

  @Test
  public void updateBoardLeftTest() {

    GameState preManagedState = new GameState(
      new int[][]{

        new int[]{0, 0, 0, 0},
        new int[]{2, 0, 4, 0},
        new int[]{2, 2, 4, 4},
        new int[]{0, 0, 2, 0}
      },
      5,
      Movement.LEFT
    );

    GameState postManagedState = service.manageGameState(preManagedState);

    Assertions.assertArrayEquals(
      postManagedState.getBoard(),
      new int[][]{
        new int[]{0, 0, 0, 0},
        new int[]{2, 4, 0, 0},
        new int[]{4, 8, 0, 0},
        new int[]{2, 0, 0, 0}
      }
    );

    Assertions.assertEquals(
      postManagedState.getMoves(),
      preManagedState.getMoves() + 1
    );

  }


  @Test
  public void updateBoardRightTest() {
    GameState preManagedState = new GameState(
      new int[][]{

        new int[]{0, 0, 0, 0},
        new int[]{2, 0, 4, 0},
        new int[]{2, 2, 4, 4},
        new int[]{0, 0, 2, 0}
      },
      5,
      Movement.RIGHT
    );

    GameState postManagedState = service.manageGameState(preManagedState);

    Assertions.assertArrayEquals(
      postManagedState.getBoard(),
      new int[][]{
        new int[]{0, 0, 0, 0},
        new int[]{0, 0, 2, 4},
        new int[]{0, 0, 2, 8},
        new int[]{0, 0, 0, 2}
      }
    );

    Assertions.assertEquals(
      postManagedState.getMoves(),
      preManagedState.getMoves() + 1
    );

  }
}
