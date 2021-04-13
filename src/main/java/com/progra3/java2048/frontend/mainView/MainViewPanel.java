package com.progra3.java2048.frontend.mainView;

import com.progra3.java2048.backend.models.GameState;
import com.progra3.java2048.backend.services.GameStateService;
import com.progra3.java2048.backend.utils.Movement;
import com.progra3.java2048.frontend.game.GameOver;
import com.progra3.java2048.frontend.game.Winner;
import com.progra3.java2048.frontend.utils.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.progra3.java2048.backend.utils.Movement.*;
import static com.progra3.java2048.backend.utils.Movement.RIGHT;
import static java.awt.event.KeyEvent.*;
import static java.awt.event.KeyEvent.VK_RIGHT;

/**
 * @author victo
 */

public class MainViewPanel {
  private final JPanel mainPanel;
  private final JPanel boardPanel;
  private final Board board;
  private final GameStateService stateService;
  private final JLabel scoreLabel;
  private final JLabel movesLabel;

  public MainViewPanel() {
    stateService = new GameStateService();
    GameState state = stateService.createGameState();
    mainPanel = new JPanel();
    mainPanel.setBounds(0, 0, 800, 600);
    mainPanel.setLayout(null);
    mainPanel.setBackground(new Color(0, 70, 0));

    movesLabel = new JLabel("Movimientos: 0");
    movesLabel.setForeground(Color.yellow);
    movesLabel.setBounds(10, 11, 150, 14);
    movesLabel.setFont(new Font("Verdana", Font.BOLD, 14));
    mainPanel.add(movesLabel);

    scoreLabel = new JLabel("Puntaje: 0");
    scoreLabel.setForeground(Color.yellow);
    scoreLabel.setBounds(150, 11, 177, 14);
    scoreLabel.setFont(new Font("Verdana", Font.BOLD, 14));
    mainPanel.add(scoreLabel);

    boardPanel = new JPanel();
    boardPanel.setLayout(new java.awt.GridLayout(4, 4, 2, 2));
    boardPanel.setBounds(144, 32, 512, 512);
    boardPanel.setBackground(new Color(70, 40, 0));
    mainPanel.add(boardPanel);

    JLabel background = new JLabel();
    background.setIcon(ImageLoader.loadIcon("res\\backgroundBoard.jpg",800,600));
    background.setBounds(0,0,800,600);
    mainPanel.add(background);

    board = new Board(boardPanel, state.getBoard());
    addListener();
  }

  public void addListener() {
    mainPanel.setFocusable(true);
    mainPanel.requestFocusInWindow();
    mainPanel.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent keyEvent) { moveTiles(keyEvent, boardPanel); }});

  }

  public void moveTiles(KeyEvent keyEvent, JPanel boardPanel) {
    Movement movement = null;
    switch (keyEvent.getKeyCode()) {
      case VK_W, VK_UP -> movement = UP;
      case VK_S, VK_DOWN -> movement = DOWN;
      case VK_A, VK_LEFT -> movement = LEFT;
      case VK_D, VK_RIGHT -> movement = RIGHT;

    }

    if (movement == null) return;

    var response = stateService.updateGameState(movement);
    board.updateBoard(response.getBoard(), boardPanel);

    displayScore(response.getScore());

    displayMoveCount(response.getMoveCount());

    if (response.isGameOver()) {
      if (response.isWinner()) displayWinPanel(response.getScore(), response.getMoveCount());
      else displayGameOver(response.getScore(), response.getMoveCount());

    }
  }

  private void displayGameOver(Integer score, Integer movements) {
    GameOver go = new GameOver(score, movements);
    mainPanel.getParent().add(go.getPanel());
    mainPanel.setVisible(false);
  }

  private void displayWinPanel(Integer score, Integer movements) {
    Winner win = new Winner(score, movements);
    mainPanel.getParent().add(win.getPanel());
    mainPanel.setVisible(false);
  }

  private void displayScore(Integer score) {
    scoreLabel.setText("Puntaje: " + score);
  }

  private void displayMoveCount(Integer moveCount) {
    movesLabel.setText("Movimientos: " + moveCount);
  }

  public JPanel getPanel() {
    return mainPanel;
  }
}

