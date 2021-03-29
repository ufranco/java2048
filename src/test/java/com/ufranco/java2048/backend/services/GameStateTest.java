package com.ufranco.java2048.backend.services;

import com.ufranco.java2048.backend.models.GameState;
import com.ufranco.java2048.backend.repositories.GameStateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static com.ufranco.java2048.backend.utils.Movement.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameStateTest {

  @Mock
  GameStateRepository repository;

  @InjectMocks
  @Spy
  GameStateService service;

  @Test
  public void updateGameStateUpMovementTest() {
    when(repository.getGameState()).thenReturn(
      new GameState(getDummyBoard(), 5)
    );

    int[][] expectedSort = new int[][]{
      new int[]{ 4, 2, 8, 4 },
      new int[]{ 0, 0, 2, 0 },
      new int[]{ 0, 0, 0, 0 },
      new int[]{ 0, 0, 0, 0 }
    };

    GameState postManagedState = service.updateGameState(UP);

    assertArrayEquals(
      expectedSort,
      postManagedState.getBoard()
    );

    assertEquals(
      6,
      postManagedState.getMoves()
    );
  }

  @Test
  public void updateGameStateDownMovementTest() {
    when(repository.getGameState()).thenReturn(
      new GameState(getDummyBoard(), 11)
    );

    int[][] expectedSort = new int[][]{
      new int[]{ 0, 0, 0, 0 },
      new int[]{ 0, 0, 0, 0 },
      new int[]{ 0, 0, 8, 0 },
      new int[]{ 4, 2, 2, 4 }
    };

    GameState postManagedState = service.updateGameState(DOWN);

    assertArrayEquals(
      expectedSort,
      postManagedState.getBoard()
    );

    assertEquals(
      12,
      postManagedState.getMoves()
    );

  }

  @Test
  public void updateGameStateLeftMovementTest() {
    when(repository.getGameState()).thenReturn(
      new GameState(getDummyBoard(), 8)
    );

    int[][] expectedSort = new int[][]{
      new int[]{ 0, 0, 0, 0 },
      new int[]{ 2, 4, 0, 0 },
      new int[]{ 4, 8, 0, 0 },
      new int[]{ 2, 0, 0, 0 }
    };

    GameState postManagedState = service.updateGameState(LEFT);

    assertArrayEquals(
      expectedSort,
      postManagedState.getBoard()

    );

    assertEquals(
      9,
      postManagedState.getMoves()

    );
  }


  @Test
  public void updateGameStateRightMovementTest() {
    when(repository.getGameState()).thenReturn(
      new GameState(getDummyBoard(), 17)
    );

    int[][] expectedSort = new int[][]{
      new int[]{ 0, 0, 0, 0 },
      new int[]{ 0, 0, 2, 4 },
      new int[]{ 0, 0, 4, 8 },
      new int[]{ 0, 0, 0, 2 }
    };

    GameState postManagedState = service.updateGameState(RIGHT);

    assertArrayEquals(
      expectedSort,
      postManagedState.getBoard()

    );

    assertEquals(
      18,
      postManagedState.getMoves()
    );

  }

  @Test
  public void updateGameOverTrueTest() {

    when(repository.getGameState()).thenReturn(
      new GameState(getDummyGameOverBoard(), 17)
    );

    assertTrue(service.updateGameState(DOWN).isGameOver());
  }

  @Test
  public void updateGameOverFalseTest() {

    when(repository.getGameState()).thenReturn(
      new GameState(getDummyBoard(), 17)
    );


    assertFalse(service.updateGameState(DOWN).isGameOver());
  }


  @Test
  public void insertValueInRandomFreePositionTest() {
    when(repository.getGameState()).thenReturn(
      new GameState(getDummyBoard(), 17)
    );

    int[][] dummyBoardWithNewValue = service.updateGameState(DOWN).getBoard();

    assertEquals(
      8,
      getNumberOfZeroes(dummyBoardWithNewValue)
    );
  }

  private int[][] getDummyBoard() {
    return new int[][]{
      new int[]{ 0, 0, 0, 0 },
      new int[]{ 2, 0, 4, 0 },
      new int[]{ 2, 2, 4, 4 },
      new int[]{ 0, 0, 2, 0 }
    };
  }

  private int[][] getDummyGameOverBoard() {
    return new int[][]{
      new int[]{ 2, 4, 16, 2 },
      new int[]{ 4, 2, 8, 128 },
      new int[]{ 2, 8, 16, 32 },
      new int[]{ 4, 2, 32, 16 }
    };
  }

  private long getNumberOfZeroes(int[][] board) {
    return Arrays.stream(board)
      .flatMapToInt(Arrays::stream)
      .filter(value -> value == 0)
      .count();
  }

}
