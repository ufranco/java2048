package com.ufranco.java2048.backend.models;

public class Cell {

	Integer x;
	Integer y;

	public Cell(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}


	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

}

