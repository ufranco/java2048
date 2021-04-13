package com.progra3.java2048.frontend.mainView;

import com.progra3.java2048.backend.models.GameState;
import com.progra3.java2048.backend.services.GameStateService;
import com.progra3.java2048.backend.utils.Movement;
import com.progra3.java2048.frontend.game.GameOver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.progra3.java2048.backend.utils.Movement.*;
import static com.progra3.java2048.backend.utils.Movement.RIGHT;
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
  private JLabel movesLabel;
  private JLabel scoreLabel;
  public MainViewPanel() {
		stateService = new GameStateService();
		GameState state = stateService.createGameState();
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 800, 600);
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Color(28, 80, 28));

    movesLabel = new JLabel("Movimientos: 0");
    movesLabel.setForeground(Color.yellow);
    movesLabel.setBounds(10, 11, 106, 14);
    mainPanel.add(movesLabel);

    scoreLabel = new JLabel("Puntaje: 0");
    scoreLabel.setForeground(Color.yellow);
    scoreLabel.setBounds(126, 11, 177, 14);
    mainPanel.add(scoreLabel);

		boardPanel = new JPanel();
		boardPanel.setLayout(new java.awt.GridLayout(4, 4, 2, 2));
		boardPanel.setBounds(144, 32, 512, 512);
		boardPanel.setBackground(new Color(76, 47, 22));
		mainPanel.add(boardPanel);
		board = new Board(boardPanel, state.getBoard());
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

		if(!validKey) return;

		var response = stateService.updateGameState(movement);
		board.updateBoard(response.getBoard(), boardPanel);

		displayScore(response.getScore());

		displayMoveCount(response.getMoveCount());

		if(response.isGameOver()) {
			if (response.isWinner()) displayWinPanel();
			else displayGameOver(response.getScore(), response.getMoveCount());

		}
	}

	private void displayGameOver(Integer score, Integer movements) {
    GameOver go = new GameOver(score, movements);
    mainPanel.getParent().add(go.getPanel());
    mainPanel.setVisible(false);
	}

	private void displayWinPanel() {
	}

	private void displayScore(Integer score) {
    scoreLabel.setText("Puntaje: "+score);
	}

	private void displayMoveCount(Integer moveCount) {
    movesLabel.setText("Movimientos: "+moveCount);
	}

	public JPanel getPanel() {
    	return mainPanel;
    }
}

