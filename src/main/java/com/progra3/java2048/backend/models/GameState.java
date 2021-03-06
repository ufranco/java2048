package com.progra3.java2048.backend.models;

import java.util.Arrays;
import java.util.stream.Collectors;

public class GameState {
	Integer[][] board;
	private boolean gameOver;
	private Integer moveCount;
	private Integer score;
	private boolean winner;

	public void setMoveCount(Integer moveCount) {
		this.moveCount = moveCount;
	}

	public Integer getMoveCount() {
		return moveCount;
	}

	public void incrementMoves() {
		this.moveCount++;
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

	@Override
	public String toString() {
		return "GameState{" +
			"board = \n" + Arrays.stream(board).map(arr -> Arrays.toString(arr)+"\n").collect(Collectors.joining())+
			"gameOver = " + gameOver +
			",\n moveCount = " + moveCount +
			",\n score = " + score +
			",\n winner = " + winner +
			"}\n";
	}
}
