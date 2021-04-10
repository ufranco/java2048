package com.ufranco.java2048.backend.models;

import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		GameState juego = new GameState(4);
		juego.setBoard(generateBoard());
		System.out.println(juego.getBoard());
		Movement dir = Movement.LEFT;
		juego.movement(dir);
		System.out.println(juego.getBoard());
	}
	
	private static ArrayList<ArrayList<Integer>> generateBoard() {
		
		ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>();
		
		ArrayList<Integer> fila = new ArrayList<Integer>();
		fila.add(2);
		fila.add(4);
		fila.add(0);
		fila.add(4);
		
		ArrayList<Integer> fila2 = new ArrayList<Integer>();
		fila2.add(4);
		fila2.add(0);
		fila2.add(0);
		fila2.add(4);
	
		ArrayList<Integer> fila3 = new ArrayList<Integer>();
		fila3.add(4);
		fila3.add(16);
		fila3.add(16);
		fila3.add(4);
		
		ArrayList<Integer> fila4 = new ArrayList<Integer>();
		fila4.add(0);
		fila4.add(0);
		fila4.add(0);
		fila4.add(0);
		
		board.add(fila);
		board.add(fila2);
		board.add(fila3);
		board.add(fila4);
		
		return board;
	}
 
}
