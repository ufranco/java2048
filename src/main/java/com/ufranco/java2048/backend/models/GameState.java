package com.ufranco.java2048.backend.models;

import java.util.ArrayList;
import java.util.List;

public class GameState {
	Integer[][] board;
	private boolean gameOver;
	private Integer moves;
	private Integer score;
	private boolean winner;

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

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}
}
