package com.progra3.java2048.frontend.game;


import com.progra3.java2048.frontend.utils.ImageLoader;

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
    panel.setBackground(new Color(74, 74, 94));
    panel.setLayout(null);

    gameOverLabel = new JLabel("");
    gameOverLabel.setIcon(ImageLoader.loadIcon("res\\derrota.png",418,173));
    gameOverLabel.setBounds(200, 114, 418, 173);
    panel.add(gameOverLabel);

    goBackButton = new JButton("");
    goBackButton.setIcon(ImageLoader.loadIcon("res\\volver a menu.png",163,70));
    goBackButton.setForeground(Color.BLACK);
    goBackButton.setBorderPainted(false);
    goBackButton.setFont(new Font("customFont", Font.BOLD | Font.ITALIC, 15));
    goBackButton.setBounds(299, 447, 163, 70);
    goBackButton.setBackground(Color.LIGHT_GRAY);
    panel.add(goBackButton);

    JLabel scoreLabel = new JLabel("Puntaje final: "+score);
    scoreLabel.setForeground(new Color(231, 196, 146));
    scoreLabel.setFont(new Font("Verdana", Font.BOLD, 18));
    scoreLabel.setBounds(500, 303, 300, 40);
    panel.add(scoreLabel);

    JLabel movesLabel = new JLabel("Movimientos: "+Movements);
    movesLabel.setForeground(new Color(231, 196, 146));
    movesLabel.setFont(new Font("Verdana", Font.BOLD, 18));
    movesLabel.setBounds(120, 303, 300, 40);
    panel.add(movesLabel);

    JLabel background = new JLabel();
    background.setIcon(ImageLoader.loadIcon("res\\backgroundGO.jpg",800,600));
    background.setBounds(0,0,800,600);
    panel.add(background);
    goBackButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        backToMainMenu();
      }
    });
  }

  private void backToMainMenu() {
    MainMenu pi = new MainMenu();
    Container parent = panel.getParent();
    panel.setVisible(false);
    parent.removeAll();
    parent.add(pi.getPanel());
  }

  public JPanel getPanel() {
    return panel;
  }
}