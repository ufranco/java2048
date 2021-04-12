package com.ufranco.java2048.backend.repositories;

import com.ufranco.java2048.backend.models.GameState;

public class GameStateRepository {
  private GameState state;

  public GameState get() {
    return deepCopy(state);
  }

  public GameState create() {
    state = null;
    state = new GameState();
    state.setBoard(generateNewBoard());
    state.setMoves(0);
    state.setGameOver(false);
    state.setScore(0);

    return deepCopy(state);
  }

  public void update(GameState state) {
    this.state = null;
    this.state = deepCopy(state);
  }

  public GameState deepCopy(GameState gameState) {
    var newGameState = new GameState();
    newGameState.setScore(gameState.getScore());
    newGameState.setMoves(gameState.getMoves());
    newGameState.setGameOver(gameState.isGameOver());

    Integer[][] board = gameState.getBoard();
    Integer[][] deepCopiedBoard = new Integer[4][4];

    for (int x = 0; x < board.length; x++) {
      System.arraycopy(board[x], 0, deepCopiedBoard[x], 0, board.length);
    }

    newGameState.setBoard(deepCopiedBoard);
    return newGameState;
  }

  private Integer[][] generateNewBoard() {

    Integer[][] board = new Integer[4][4];
    for(int x = 0; x < board.length; x++) {
      for(int y = 0; y < board.length; y++) {
        board[x][y] = 0;
      }
    }
    return board;
  }
}
