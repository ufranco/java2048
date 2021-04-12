package com.ufranco.java2048.frontend.mainView;

import com.ufranco.java2048.backend.services.GameStateService;
import com.ufranco.java2048.backend.utils.Movement;
import com.ufranco.java2048.frontend.utils.CargarImagenes;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Board {
    private ArrayList<JLabel> tiles;
    private final Icon tile = CargarImagenes.cargarIcon("res\\tile.png", 128, 128);
    private final Icon emptyTile = CargarImagenes.cargarIcon("res\\tile0.png", 128, 128);
    private final GameStateService stateService;

    Board(JPanel panel, GameStateService stateService){
    	this.stateService = stateService;
        fillArray();
        createBoard(panel);
    }

    private void fillArray() {
        var tiles = new ArrayList<JLabel>();
        Integer[][] board = stateService.getGameState().getBoard();
        int count = 0;

			for (Integer[] integers : board) {
				for (Integer integer : integers) {
					tiles.add(new JLabel(integer.toString()));

					JLabel label = tiles.get(count);
					label.setIcon(emptyTile);
					label.setOpaque(true);
					label.setForeground(new Color(255, 255, 0));
					label.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
					label.setFont(new Font("Verdana", Font.BOLD, 20));
					label.setHorizontalTextPosition(JLabel.CENTER);
					label.setVerticalTextPosition(JLabel.CENTER);
					count++;
				}
			}
    }
    private void createBoard(JPanel panel) {
        tiles.forEach(panel::add);
    }

    public void updateBoard(Integer[][] values, JPanel panel) {
        for (int x = 0; x< values.length; x++) {
        	for(int y = 0;y< values.length; y++) {
	        	var label = (JLabel)panel.getComponent(x);
	        	changeTile(label, values[x][y]);
	            int green =label.getForeground().getGreen()-10;
	            if (green > 0)
	            	label.setForeground(new Color(255, green, 0));
        	}
        }

    }
    public void changeTile(JLabel l, Integer value) {
    	if (!l.getText().equals(value.toString())) {
    		if(value == 0) {
    			l.setText("");
    			l.setIcon(CargarImagenes.cargarIcon("res\\tile0.png", 128, 128));
    		}
    		else {
    			l.setText(value.toString());
    			l.setIcon(tile);
    		}
    	}
    }
	public void moveTiles(KeyEvent KE, JPanel boardPanel) {
		switch (KE.getKeyCode()) {
			case KeyEvent.VK_W, KeyEvent.VK_UP -> {
				Integer[][] board = stateService.updateGameState(Movement.UP).getBoard();
				updateBoard(board, boardPanel);
			}
			case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {
				Integer[][] board = stateService.updateGameState(Movement.DOWN).getBoard();
				updateBoard(board, boardPanel);
			}
			case KeyEvent.VK_A, KeyEvent.VK_LEFT -> {
				Integer[][] board = stateService.updateGameState(Movement.LEFT).getBoard();
				updateBoard(board, boardPanel);
			}
			case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> {
				Integer[][] board = stateService.updateGameState(Movement.RIGHT).getBoard();
				updateBoard(board, boardPanel);
			}
		}
	}

}