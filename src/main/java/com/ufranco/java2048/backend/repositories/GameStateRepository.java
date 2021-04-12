package com.ufranco.java2048.backend.repositories;

import com.ufranco.java2048.backend.models.GameState;

import java.util.Arrays;

public class GameStateRepository {

  private GameState state;

  public GameState get() {
    return state;
  }

  public GameState create() {
    state = new GameState();
    return deepCopy(state);
  }

  public void update(GameState state) {
    this.state = deepCopy(state);

    System.out.println( "stored as\n" +
      Arrays.toString(state.getBoard()[0])
      +"\n"+Arrays.toString(state.getBoard()[1])
      +"\n"+Arrays.toString(state.getBoard()[2])
      +"\n"+Arrays.toString(state.getBoard()[3])+"\n"
    );
  }

  public GameState deepCopy(GameState gameState) {
    var newGameState = new GameState();
    newGameState.setScore(gameState.getScore());
    newGameState.setMoves(gameState.getMoves());
    newGameState.setGameOver(gameState.isGameOver());

    Integer[][] board = gameState.getBoard();
    Integer[][] deepCopiedBoard = new Integer[4][4];

    for (int x = 0; x < board.length; x++) {
      for (int y = 0; y < board.length; y++) {
        deepCopiedBoard[x][y] = board[x][y];
      }
    }

    newGameState.setBoard(deepCopiedBoard);
    return newGameState;
  }
}
