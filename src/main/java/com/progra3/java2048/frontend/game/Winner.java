package com.progra3.java2048.frontend.game;

import com.progra3.java2048.frontend.utils.ImageLoader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Winner {
  private JPanel panel;
  public Winner(Integer score, Integer Movements) {
    panel = new JPanel();
    panel.setBackground(Color.YELLOW);
    panel.setBounds(0, 0, 800, 600);
    panel.setLayout(null);

    JButton goBackButton = new JButton("");
    goBackButton.setIcon(ImageLoader.loadIcon("res\\volver a menu victoria.png", 163, 70));
    goBackButton.setForeground(new Color(0,0,0, 0));
    goBackButton.setBorderPainted(false);
    goBackButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        backToMainMenu();
      }
    });
    goBackButton.setFont(new Font("customFont", Font.BOLD | Font.ITALIC, 15));
    goBackButton.setBackground(new Color(231, 10, 10, 0));
    goBackButton.setBackground(Color.YELLOW);
    goBackButton.setBounds(332, 346, 163, 70);
    panel.add(goBackButton);

    JLabel victoria = new JLabel("");
    victoria.setBackground(Color.GRAY);
    victoria.setIcon(ImageLoader.loadIcon("res\\cartel victoria.png",418,173));
    victoria.setForeground(Color.BLACK);
    victoria.setFont(new Font("customFont", Font.BOLD | Font.ITALIC, 24));
    victoria.setBounds(200, 114, 418, 173);
    panel.add(victoria);

    JLabel scoreLabel = new JLabel("Puntaje final: "+score);
    scoreLabel.setForeground(new Color(28, 252, 11));
    scoreLabel.setFont(new Font("Verdana", Font.BOLD, 18));
    scoreLabel.setBounds(530, 303, 300, 40);
    panel.add(scoreLabel);

    JLabel movesLabel = new JLabel("Movimientos: "+Movements);
    movesLabel.setForeground(new Color(28, 252, 11));
    movesLabel.setFont(new Font("Verdana", Font.BOLD, 18));
    movesLabel.setBounds(120, 303, 300, 40);
    panel.add(movesLabel);

    JLabel decoration1 = new JLabel("");
    decoration1.setBackground(new Color(240, 240, 1, 0));
    decoration1.setIcon(ImageLoader.loadIcon("res\\victoria1.png",80,200));
    decoration1.setBounds(162, 400, 80, 200);
    panel.add(decoration1);

    JLabel decoration2 = new JLabel("");
    decoration2.setIcon(ImageLoader.loadIcon("res\\victoria2.png", 150,150));
    decoration2.setBounds(533, 450, 150, 150);
    panel.add(decoration2);

    JLabel decoration3 = new JLabel("");
    decoration3.setIcon(ImageLoader.loadIcon("res\\victoria3.png",150,150));
    decoration3.setBounds(316, 462, 150, 150);
    panel.add(decoration3);

    JLabel background = new JLabel();
    background.setIcon(ImageLoader.loadIcon("res\\backgroundWin.jpg",800,600));
    background.setBounds(0,0,800,600);
    panel.add(background);

  }
  private void backToMainMenu() {
    MainMenu pi = new MainMenu();
    panel.getParent().add(pi.getPanel());
    panel.setVisible(false);

  }
  public JPanel getPanel() {return panel;}

}
