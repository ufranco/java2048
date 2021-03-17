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
import static org.mockito.Mockito.*;

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

    doReturn(
      new int[][]{
        new int[]{ 4, 2, 8, 4 },
        new int[]{ 0, 2, 2, 0 },
        new int[]{ 0, 0, 0, 0 },
        new int[]{ 0, 0, 0, 0 }
      })
      .when(service).insertValueRandomInFreePosition(expectedSort);

    GameState postManagedState = service.updateGameState(UP);

    assertArrayEquals(
      new int[][]{
        new int[]{ 4, 2, 8, 4 },
        new int[]{ 0, 2, 2, 0 },
        new int[]{ 0, 0, 0, 0 },
        new int[]{ 0, 0, 0, 0 }
      },
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

    doReturn(
      new int[][]{
        new int[]{ 2, 0, 0, 0 },
        new int[]{ 0, 0, 0, 0 },
        new int[]{ 0, 0, 8, 0 },
        new int[]{ 4, 2, 2, 4 }
      })
      .when(service).insertValueRandomInFreePosition(expectedSort);

    GameState postManagedState = service.updateGameState(DOWN);

    assertArrayEquals(
      new int[][]{
        new int[]{ 2, 0, 0, 0 },
        new int[]{ 0, 0, 0, 0 },
        new int[]{ 0, 0, 8, 0 },
        new int[]{ 4, 2, 2, 4 }
      },
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

    doReturn(
      new int[][]{
        new int[]{ 0, 0, 0, 0 },
        new int[]{ 2, 4, 0, 0 },
        new int[]{ 4, 8, 0, 2 },
        new int[]{ 2, 0, 0, 0 }
      })
      .when(service).insertValueRandomInFreePosition(expectedSort);

    GameState postManagedState = service.updateGameState(LEFT);

    assertArrayEquals(
      new int[][]{
        new int[]{ 0, 0, 0, 0 },
        new int[]{ 2, 4, 0, 0 },
        new int[]{ 4, 8, 0, 2 },
        new int[]{ 2, 0, 0, 0 }
      },
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

    doReturn(
      new int[][]{
        new int[]{ 0, 0, 0, 0 },
        new int[]{ 0, 0, 2, 4 },
        new int[]{ 2, 0, 4, 8 },
        new int[]{ 0, 0, 0, 2 }
      })
      .when(service).insertValueRandomInFreePosition(expectedSort);

    GameState postManagedState = service.updateGameState(RIGHT);

    assertArrayEquals(
      postManagedState.getBoard(),
      new int[][]{
        new int[]{ 0, 0, 0, 0 },
        new int[]{ 0, 0, 2, 4 },
        new int[]{ 2, 0, 4, 8 },
        new int[]{ 0, 0, 0, 2 }
      }
    );

    assertEquals(
      18,
      postManagedState.getMoves()
    );

  }

  @Test
  public void updateGameStateCallsGameOverTest() {
    int[][] board = getDummyBoard();

    when(repository.getGameState()).thenReturn(
      new GameState(board, 17)
    );

    service.updateGameState(UP);

    verify(service, times(1))
      .isGameOver(board);
  }

  @Test
  public void updateGameOverTrueTest() {
    assertTrue(
      service.isGameOver(getDummyGameOverBoard())
    );
  }

  @Test
  public void updateGameOverFalseTest() {
    assertFalse(service.isGameOver(getDummyBoard()));
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

  @Test
  public void insertValueInRandomFreePositionTest() {
    int[][] dummyBoardWithNewValue =
      service.insertValueRandomInFreePosition(
        getDummyBoard()
      );

    assertEquals(
      8,
      getNumberOfZeroes(dummyBoardWithNewValue)
    );
  }

  private long getNumberOfZeroes(int[][] board) {
    return Arrays.stream(board)
      .flatMapToInt(Arrays::stream)
      .filter(value -> value == 0)
      .count();
  }

  @Test
  public void updateGameStateCallsInsertValueInRandomFreePositionWhenIsGameOverReturnsFalseTest() {
    when(repository.getGameState()).thenReturn(
      new GameState(getDummyBoard(), 5)
    );

    doReturn(false)
      .when(service).isGameOver(getDummyBoard());

    service.updateGameState(DOWN);

    verify(service, times(1))
      .insertValueRandomInFreePosition(getDummyBoard());

  }

}
