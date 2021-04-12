package com.ufranco.java2048.backend.models;

import java.util.ArrayList;
import java.util.List;

public class GameState {
	  Integer[][] board;
	  private boolean gameOver;
		private Integer moves;
		private Integer score;

	  public GameState() {
	  	Integer[][] board = new Integer[4][4];
			for(int x = 0; x < board.length; x++) {
				for(int y = 0; y < board.length; y++) {
					board[x][y] = 0;
				}
			}

	    this.board = board;
	    this.moves = 0;
	    this.gameOver = false;
	    this.score = 0;

	  }

		public void setMoves(Integer moves) {
			this.moves = moves;
		}

	  public Integer getMoves() {
	    return moves;
	  }

	  public void incrementMoves() {
	    this.moves++;
	  }

	  public Integer[][] getBoard() {
	    return this.board;
	  }

	  public void setBoard(Integer[][] board) {
	    this.board = board;
	  }

	  public boolean isGameOver() {
	    return this.gameOver;
	  }

	  public void setGameOver(boolean isGameOver) {
	    this.gameOver = isGameOver;

	  }

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}
