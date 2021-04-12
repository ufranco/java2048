package com.ufranco.java2048.frontend.game;


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

	public GameOver(){
		panel = new JPanel();
    	panel.setBounds(0, 0, 800, 600);
    	panel.setLayout(null);

    	gameOverLabel = new JLabel("juejue perdiste wey");
    	gameOverLabel.setBounds(200, 200, 100, 100);
    	panel.add(gameOverLabel);

    	goBackButton = new JButton("Volver al inicio");
    	goBackButton.setBounds(400, 400, 100, 100);
    	panel.add(goBackButton);

    	goBackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				backToMainMenu();
			}
		});
    	goBackButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				backToMainMenu();
			}
		});
	}
	private void backToMainMenu() {
		PantallaInicial pi = new PantallaInicial();
		this.panel.getParent().add(pi.getPanel());
		this.panel.setVisible(false);
	}

	public JPanel getPanel() {
		return this.panel;
	}
}
