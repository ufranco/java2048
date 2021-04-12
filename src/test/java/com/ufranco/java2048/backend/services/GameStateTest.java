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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameStateTest {

  @Mock
  GameStateRepository repository = new GameStateRepository();

  @Spy
  @InjectMocks
  GameStateService service = new GameStateService(repository);

  @Test
  public void updateGameStateUpMovementTest() {
    when(repository.get()).thenReturn(
      generateGameState(getDummyBoard(),5)
    );

    Integer[][] expectedSort = new Integer[][]{
      new Integer[]{ 4, 2, 8, 4 },
      new Integer[]{ 0, 0, 2, 0 },
      new Integer[]{ 0, 0, 0, 0 },
      new Integer[]{ 0, 0, 0, 0 }
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
    when(repository.get()).thenReturn(
      generateGameState(getDummyBoard(),11)
    );


    Integer[][] expectedSort = new Integer[][]{
      new Integer[]{ 0, 0, 0, 0 },
      new Integer[]{ 0, 0, 0, 0 },
      new Integer[]{ 0, 0, 8, 0 },
      new Integer[]{ 4, 2, 2, 4 }
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
    when(repository.get()).thenReturn(
      generateGameState(getDummyBoard(),8)
    );

    Integer[][] expectedSort = new Integer[][]{
      new Integer[]{ 0, 0, 0, 0 },
      new Integer[]{ 2, 4, 0, 0 },
      new Integer[]{ 4, 8, 0, 0 },
      new Integer[]{ 2, 0, 0, 0 }
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
    when(repository.get()).thenReturn(
      generateGameState(getDummyBoard(),17)
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

    when(repository.get()).thenReturn(
      generateGameState(getDummyGameOverBoard(),17)
    );

    assertTrue(service.updateGameState(DOWN).isGameOver());
  }

  @Test
  public void updateGameOverFalseTest() {
    when(repository.get()).thenReturn(
      generateGameState(getDummyBoard(),17)
    );

    assertFalse(service.updateGameState(DOWN).isGameOver());
  }


  @Test
  public void insertValueInRandomFreePositionTest() {
    when(repository.get()).thenReturn(
      generateGameState(getDummyBoard(),17)
    );

    Integer[][] dummyBoardWithNewValue = service.updateGameState(DOWN).getBoard();

    assertEquals(
      10,
      getNumberOfZeroes(dummyBoardWithNewValue)
    );
  }

  private GameState generateGameState(Integer[][] board, Integer moves) {
    var gameState = new GameState();
    gameState.setBoard(board);
    gameState.setMoves(moves);
    return gameState;
  }

  private Integer[][] getDummyBoard() {
    return new Integer[][]{
      new Integer[]{ 0, 0, 0, 0 },
      new Integer[]{ 2, 0, 4, 0 },
      new Integer[]{ 2, 2, 4, 4 },
      new Integer[]{ 0, 0, 2, 0 }
    };
  }

  private Integer[][] getDummyGameOverBoard() {
    return new Integer[][]{
      new Integer[]{ 2, 4, 16, 2 },
      new Integer[]{ 4, 2, 8, 128 },
      new Integer[]{ 2, 8, 16, 32 },
      new Integer[]{ 4, 2, 32, 16 }
    };
  }

  private long getNumberOfZeroes(Integer[][] board) {
    return Arrays.stream(board)
      .flatMap(Arrays::stream)
      .filter(value -> value == 0)
      .count();
  }

}
