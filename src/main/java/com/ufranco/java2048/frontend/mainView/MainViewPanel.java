package com.ufranco.java2048.frontend.mainView;

import com.ufranco.java2048.backend.services.GameStateService;
import com.ufranco.java2048.backend.utils.Movement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.ufranco.java2048.backend.utils.Movement.*;
import static com.ufranco.java2048.backend.utils.Movement.RIGHT;
import static java.awt.event.KeyEvent.*;
import static java.awt.event.KeyEvent.VK_RIGHT;

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
				moveTiles(KE, boardPanel);
			}
		});

    }

	public void moveTiles(KeyEvent keyEvent, JPanel boardPanel) {
		Movement movement = null;
		var validKey = false;

		switch (keyEvent.getKeyCode()) {
			case VK_W, VK_UP -> {
				movement = UP;
				validKey = true;
			}
			case VK_S, VK_DOWN -> {
				movement = DOWN;
				validKey = true;
			}
			case VK_A, VK_LEFT -> {
				movement = LEFT;
				validKey = true;
			}
			case VK_D, VK_RIGHT -> {
				movement = RIGHT;
				validKey = true;
			}

		}

		if(validKey) {
			var response = stateService.updateGameState(movement);
			board.updateBoard(response.getBoard(), boardPanel);

			displayScore(response.getScore());

			displayMoveCount(response.getMoveCount());

			if(response.isGameOver()) {
				if (response.isWinner()) displayWinPanel();
			 	else displayGameOver();

			}
		}
	}

	private void displayGameOver() {

	}

	private void displayWinPanel() {

	}

	private void displayScore(Integer score) {

	}

	private void displayMoveCount(Integer moveCount) {

	}

	public JPanel getPanel() {
    	return mainPanel;
    }
}

