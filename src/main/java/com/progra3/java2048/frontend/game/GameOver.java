package com.progra3.java2048.frontend.game;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOver {
  JPanel panel;
  JLabel gameOverLabel;
  JButton goBackButton;

  public GameOver(Integer score, Integer Movements) {
    panel = new JPanel();
    panel.setBounds(0, 0, 800, 600);
    panel.setLayout(null);

    gameOverLabel = new JLabel("GAME OVER JUE JUE");
    gameOverLabel.setBounds(200, 114, 418, 173);
    panel.add(gameOverLabel);

    goBackButton = new JButton("Volver al inicio");
    goBackButton.setForeground(Color.BLACK);
    goBackButton.setFont(new Font("customFont", Font.BOLD | Font.ITALIC, 15));
    goBackButton.setBounds(299, 347, 163, 70);
    goBackButton.setBackground(Color.LIGHT_GRAY);
    panel.add(goBackButton);

    JLabel scoreLabel = new JLabel("Puntaje: "+score);
    scoreLabel.setBounds(485, 303, 166, 14);
    panel.add(scoreLabel);

    JLabel movesLabel = new JLabel("Movimientos: "+Movements);
    movesLabel.setBounds(73, 303, 172, 14);
    panel.add(movesLabel);

    goBackButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        backToMainMenu();
      }
    });
  }

  private void backToMainMenu() {
    MainMenu pi = new MainMenu();
    panel.getParent().add(pi.getPanel());

    panel.setVisible(false);
  }

  public JPanel getPanel() {
    return panel;
  }
}
