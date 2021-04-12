package com.ufranco.java2048.backend.models;

import com.ufranco.java2048.backend.utils.Movement;

import pruebasTP.tuplaPosiciones;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class GameState {
	  ArrayList<ArrayList<Integer>> board;
	  private boolean gameOver;
	  private Integer moves;
	  private int boardSize;


	  public GameState(int boardSize) {
	    this.board = new ArrayList<ArrayList<Integer>>();
	    this.moves = 0;
	    this.gameOver = false;
	    this.boardSize=boardSize;
	    fillBoard();
	    positionNumberinBoard(listEmptyPositions(this.board));
	    positionNumberinBoard(listEmptyPositions(this.board));
	  }
	  
	  private void fillBoard(){
		  while(this.board.size() < this.boardSize) {
			  ArrayList<Integer> row = new ArrayList<Integer>();
			  while(row.size()<this.boardSize) {
				  row.add(0);
			  }
			  board.add(row);
		  }
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

	  private ArrayList<ArrayList<Integer>> positionNumberinBoard(ArrayList<ListPositions> arrayList){

		int number = generateNumberBoard();
	    int ubicacion = new Random().nextInt(arrayList.size());
	    int fila = arrayList.get(ubicacion).fila;
	    int columna = arrayList.get(ubicacion).columna;

	    if(this.board.get(fila).get(columna) == 0) {
	      this.board.get(fila).remove(columna);
	      this.board.get(fila).add(columna,number);
	    }
	    

	    return board;

	  }

	  private static ArrayList<ListPositions> listEmptyPositions(ArrayList<ArrayList<Integer>> board){

	    ArrayList<ListPositions> vacios = new ArrayList<ListPositions>();

	    for(int i=0; i < board.size(); i++) {
	      for(int z=0; z < board.get(i).size(); z++) {
	        if(board.get(i).get(z)==0) {
	          ListPositions tupla= new ListPositions(i,z);
	          vacios.add(tupla);
	        }
	      }
	    }

	    return vacios;
	  }
	  public void movement(Movement dir) {
		  
		  if(dir.equals(Movement.RIGHT) || dir.equals(Movement.LEFT)) {
			  
			  for (int i=0;i<this.board.size();i++) {
				  
				  if(dir.equals(Movement.RIGHT)) {
					  	withoutZeros(board.get(i));
						sum(board.get(i));
						completeRow(board.get(i),dir);
				  }
				  if(dir.equals(Movement.LEFT)) {
					withoutZeros(board.get(i));
					sum(board.get(i));
					completeRow(board.get(i),dir);
				  }
			  }
		  }
	  }

	  private void withoutZeros(ArrayList<Integer> fila) {
			while(fila.contains(0)) {
				fila.remove(fila.indexOf(0));
			}
		}
		private void sum(ArrayList<Integer> fila) {
			int sum=0;
			
			for (int i=0; i < (fila.size()-1);i++) {
				if(fila.get(i).equals(fila.get(i+1))){
					sum = fila.get(i) + fila.get(i+1);
					fila.remove(i+1);
					fila.remove(i);
					fila.add(i,sum);
				}
			}
		}
		private void completeRow (ArrayList<Integer> fila,Movement dir) {
			while(fila.size() < this.board.size()) {
				if(dir.equals(Movement.RIGHT)) {
					fila.add(0,0);
				}
				else {
					fila.add(0);
				}
			}
		}

	  public Integer getMoves() {
	    return moves;
	  }

	  public void incrementMoves() {
	    this.moves++;
	  }

	  public ArrayList<ArrayList<Integer>> getBoard() {
	    return this.board;
	  }

	  public void setBoard(ArrayList<ArrayList<Integer>> board) {
	    this.board = board;
	  }

	  public boolean isGameOver() {
	    return this.gameOver;
	  }

	  public void setGameOver(boolean isGameOver) {
	    this.gameOver = isGameOver;

	  }

	}
