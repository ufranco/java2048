package com.ufranco.java2048.backend.models;

import com.ufranco.java2048.backend.utils.Movement;

import java.util.LinkedList;
import java.util.Random;

public class GameState {
  private int[][] board;
  private boolean gameOver;
  private Integer moves;


  public GameState() {
    this.board = new int[4][4];
    this.moves = 0;
    this.gameOver = false;
    LinkedList<tuplaPosiciones> posVacias = posicionesvacias(this.board);
    posicionarNumberinBoard(this.board,generateNumberBoard(),posVacias);
  }

  public GameState(int[][] board, Integer moves) {
        this.board = board;
        this.moves = moves;
      }

  private static int generateNumberBoard() {
    int number= new Random().nextInt(2);

    if(number == 0) {
      return 2;
    }
    else {
      return 4;
    }
  }

  private static int[][] posicionarNumberinBoard(int[][] board, int number, LinkedList<tuplaPosiciones> posVacias){

    int ubicacion = new Random().nextInt(posVacias.size());
    int fila = posVacias.get(ubicacion).fila;
    int columna = posVacias.get(ubicacion).columna;

    if(board[fila][columna] == 0) {
      board[fila][columna] = number;
    }

    return board;

  }

  private static LinkedList<tuplaPosiciones> posicionesvacias(int[][] board){

    LinkedList<tuplaPosiciones> vacios = new LinkedList<tuplaPosiciones>();

    for(int i=0; i < board.length; i++) {
      for(int z=0; z < board[i].length; z++) {
        if(board[i][z]==0) {
          tuplaPosiciones tupla= new tuplaPosiciones(i,z);
          vacios.add(tupla);
        }
      }
    }

    return vacios;
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
