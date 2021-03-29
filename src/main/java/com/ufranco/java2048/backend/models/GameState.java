package com.ufranco.java2048.backend.models;

import com.ufranco.java2048.backend.utils.Movement;

public class GameState {
  private int[][] board;
  private boolean gameOver;
  private Integer moves;


  public GameState() {
    this.board = new int[4][4];
    this.moves = 0;
    this.gameOver = false;
  }

  public GameState(int[][] board, Integer moves) {
        this.board = board;
        this.moves = moves;
      }

  public Integer getMoves() {
    return moves;
  }

  public void incrementMoves() {
    this.moves++;
  }

  public int[][] getBoard() {
    return this.board;
  }

  public void setBoard(int[][] board) {
    this.board = board;
  }

  public boolean isGameOver() {
    return this.gameOver;
  }

  public void setGameOver(boolean isGameOver) {
    this.gameOver = isGameOver;

  }

}
