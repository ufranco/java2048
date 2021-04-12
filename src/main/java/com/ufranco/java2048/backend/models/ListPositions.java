package com.ufranco.java2048.backend.models;

public class ListPositions {
	
	int fila;
	int columna;
	
	public ListPositions(int fila, int columna) {
		this.fila=fila;
		this.columna=columna;
	}
	
	
	public int getFila() {
		return this.fila;
	}

	public int getColumna() {
		return this.columna;
	}

}

