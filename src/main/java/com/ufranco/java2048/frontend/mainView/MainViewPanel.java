package com.ufranco.java2048.frontend.mainView;

import com.ufranco.java2048.backend.services.GameStateService;
import com.ufranco.java2048.frontend.game.GameOver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author victo
 */
public class MainViewPanel {
	private final JPanel mainPanel;
	private final JPanel boardPanel;
  private final Board board;
  private final GameStateService stateService;

	public MainViewPanel() {
		stateService = new GameStateService();
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 800, 600);
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Color(0,70,0));

		boardPanel = new JPanel();
		boardPanel.setLayout(new java.awt.GridLayout(4, 4, 2, 2));
		boardPanel.setBounds(144, 32, 512, 512);
		boardPanel.setBackground(new Color(70, 40, 0));
		mainPanel.add(boardPanel);
		board = new Board(boardPanel, stateService);
		addListener();
	}

    public void addListener() {
    	mainPanel.setFocusable(true);
    	mainPanel.requestFocusInWindow();
			mainPanel.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent KE) {
				isOver();
				board.moveTiles(KE, boardPanel);
			}
		});
    }
    private void isOver() {
    	/*if(stateService.createGameState().isGameOver()) {
    		var gameOver = new GameOver();
    		mainPanel.getParent().add(gameOver.getPanel());
    		mainPanel.setVisible(false);
    	}*/
    }
    public JPanel getPanel() {
    	return mainPanel;
    }
}

