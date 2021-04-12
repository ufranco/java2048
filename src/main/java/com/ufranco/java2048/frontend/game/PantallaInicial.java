package com.ufranco.java2048.frontend.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ufranco.java2048.frontend.mainView.MainViewPanel;
import com.ufranco.java2048.frontend.utils.CargarImagenes;

public class PantallaInicial {
	private final JPanel panel;
	public PantallaInicial() {
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 800, 600);
		panel.setLayout(null);
		setUI(panel);
	}

	public void setUI(JPanel panel) {
		JButton newGameButton = new JButton("");
		newGameButton.setIcon(CargarImagenes.cargarIcon("res\\juego nuevo.png",163, 70));
		newGameButton.setForeground(Color.BLACK);
		newGameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				empezarPartida(panel);
			}
		});
		newGameButton.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent KE) {;
				empezarPartida(panel);
			}
		});
		newGameButton.setFont(new Font("customFont", Font.BOLD | Font.ITALIC, 15));
		newGameButton.setBackground(Color.LIGHT_GRAY);
		newGameButton.setBounds(332, 279, 163, 70);
		panel.add(newGameButton);

		JLabel WelcomeSign = new JLabel("");
		WelcomeSign.setBackground(Color.GRAY);
		WelcomeSign.setIcon(CargarImagenes.cargarIcon("res\\bienvenido.png",418, 173));
		WelcomeSign.setForeground(Color.BLACK);
		WelcomeSign.setFont(new Font("customFont", Font.BOLD | Font.ITALIC, 24));
		WelcomeSign.setBounds(207, 34, 418, 173);
		panel.add(WelcomeSign);

		JLabel ImagenCadenaSuperior = new JLabel("New label");
		ImagenCadenaSuperior.setIcon(CargarImagenes.cargarIcon("res\\parte superior.png", 172, 132));
		ImagenCadenaSuperior.setBounds(0, 0, 172, 132);
		panel.add(ImagenCadenaSuperior);

		JLabel ImagenCadenaInferior = new JLabel("New label");
		ImagenCadenaInferior.setIcon(CargarImagenes.cargarIcon("res\\parte inferior.png",161, 127));
		ImagenCadenaInferior.setBounds(639, 473, 161, 127);
		panel.add(ImagenCadenaInferior);
	}
	public void empezarPartida(JPanel panel) {
		MainViewPanel mv = new MainViewPanel();
		panel.getParent().add(mv.getPanel());
		panel.setVisible(false);

	}
	public JPanel getPanel() {
		return this.panel;

	}
}
