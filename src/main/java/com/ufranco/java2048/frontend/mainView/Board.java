package com.ufranco.java2048.frontend.mainView;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import com.ufranco.java2048.backend.services.GameStateService;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import com.ufranco.java2048.frontend.utils.CargarImagenes;
import com.ufranco.java2048.backend.utils.Movement;

public class Board {
    private ArrayList<JLabel> tiles;
    private Icon tile = CargarImagenes.cargarIcon("res\\tile.png", 128, 128);
    private Icon emptyTile = CargarImagenes.cargarIcon("res\\tile0.png", 128, 128);
    private GameStateService gss;
   
    Board(JPanel panel, GameStateService gs){
    	this.gss = gs;
        fillArray();
        createBoard(panel);
    }
    
    private void fillArray(){
        tiles = new ArrayList<JLabel>();
        Integer[][]board = gss.getGameState().getBoard();
        int contador = 0;
        
        for(int i=0; i<board.lenght; i++){
        	
        	for(int j=0;j<board.lenght;j++) {
        		tiles.add(new JLabel(board[i][j].toString()));
	            
        		JLabel l = tiles.get(contador);
	            l.setIcon(emptyTile);
	            l.setOpaque(true);
	            l.setForeground(new Color(255, 255, 0));
	            l.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
	            l.setFont(new Font("Verdana", Font.BOLD, 20));
	    		l.setHorizontalTextPosition(JLabel.CENTER);
	    		l.setVerticalTextPosition(JLabel.CENTER);
	    		contador++;
        	}
        }
    }
    private void createBoard(JPanel panel){
        tiles.forEach(label -> {
        panel.add(label);
        });
    }
    
    public void updateBoard(Integer[][] values, JPanel panel) {
        for (int i=0; i<values.lenght; i++) {
        	for(int j=0;j<values.lenght; j++) {
	        	JLabel l = (JLabel)panel.getComponent(i);
	        	changeTile(l, values[i][j]);
	            int green =l.getForeground().getGreen()-10;
	            if (green > 0)
	            	l.setForeground(new Color(255, green, 0));
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
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:{
				Integer[][] board = gss.updateGameState(Movement.UP).getBoard();
				updateBoard(board,boardPanel);
				break;
			}
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:{
				Integer[][] board = gss.updateGameState(Movement.DOWN).getBoard();
				updateBoard(board,boardPanel);
				break;
			}
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:{
				Integer[][] board = gss.updateGameState(Movement.LEFT).getBoard();
				updateBoard(board,boardPanel);
				break;
			}
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:{
				Integer[][] board = gss.updateGameState(Movement.RIGHT).getBoard();
				updateBoard(board,boardPanel);
				break;
			}
		}
	}

}