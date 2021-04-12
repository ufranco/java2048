package com.ufranco.java2048.frontend.mainView;

import java.awt.event.KeyAdapter;

import javax.swing.JPanel;

import com.ufranco.java2048.backend.services.GameStateService;
import com.ufranco.java2048.frontend.game.GameOver;

import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
/**
 *
 * @author victo
 */
public class MainViewPanel {
	
	private JPanel mainPanel;
    private JPanel boardPanel;
    private Board board;
    private GameStateService gs;
    
    public MainViewPanel(){
    	gs = new GameStateService();
    	this.mainPanel = new JPanel();
    	mainPanel.setBounds(0, 0, 800, 600);
    	mainPanel.setLayout(null);
    	mainPanel.setBackground(new Color(0,70,0));

    	this.boardPanel = new JPanel();
        boardPanel.setLayout(new java.awt.GridLayout(4, 4, 2, 2));
        boardPanel.setBounds(144, 32, 512, 512);
        boardPanel.setBackground(new Color(70, 40, 0));
        mainPanel.add(boardPanel);
        this.board= new Board(boardPanel, gs);
        addListener();
    }
   
    public void addListener() {
    	mainPanel.setFocusable(true);
    	mainPanel.requestFocusInWindow();
        mainPanel.addKeyListener(new KeyAdapter() {
    		
			@Override
			public void keyPressed(KeyEvent KE) {
				isOver();
				board.moveTiles(KE, boardPanel);
			}
		});
    }
    private void isOver() {
  
    	if(gs.getGameState().isGameOver()) {
    		GameOver go = new GameOver();
    		this.mainPanel.getParent().add(go.getPanel());
    		this.mainPanel.setVisible(false);
    	}
    }
    public JPanel getPanel() {
    	return this.mainPanel;
    }
}

