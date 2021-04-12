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
	private JPanel panel;
	public PantallaInicial(){
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 800, 600);
		panel.setLayout(null);
		setUI(panel);
	}
	public void setUI(JPanel panel) {
		JButton BotonJuegoNuevo = new JButton("");
		BotonJuegoNuevo.setIcon(CargarImagenes.cargarIcon("res\\juego nuevo.png",163, 70));
		BotonJuegoNuevo.setForeground(Color.BLACK);
		BotonJuegoNuevo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				empezarPartida(panel);
			}
		});
		BotonJuegoNuevo.addKeyListener(new KeyAdapter() {
    		
			@Override
			public void keyPressed(KeyEvent KE) {;
				empezarPartida(panel); 
			}
		});
		BotonJuegoNuevo.setFont(new Font("customFont", Font.BOLD | Font.ITALIC, 15));
		BotonJuegoNuevo.setBackground(Color.LIGHT_GRAY);
		BotonJuegoNuevo.setBounds(332, 279, 163, 70);
		panel.add(BotonJuegoNuevo);
		
		JLabel CartelBienvenida = new JLabel("");
		CartelBienvenida.setBackground(Color.GRAY);
		CartelBienvenida.setIcon(CargarImagenes.cargarIcon("res\\bienvenido.png",418, 173));
		CartelBienvenida.setForeground(Color.BLACK);
		CartelBienvenida.setFont(new Font("customFont", Font.BOLD | Font.ITALIC, 24));
		CartelBienvenida.setBounds(207, 34, 418, 173);
		panel.add(CartelBienvenida);
		
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
