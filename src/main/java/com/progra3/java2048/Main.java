package com.progra3.java2048;

import com.progra3.java2048.frontend.game.MainMenu;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class Main {

	private final JFrame frame;

	Main() {
		frame = new JFrame();
		frame.setTitle("2048");
		frame.getContentPane().setSize(new Dimension(800, 600));
		frame.getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frame.getContentPane().setBounds(new Rectangle(0, 0, 800, 600));
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 818, 641);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainMenu pi = new MainMenu();
		frame.getContentPane().add(pi.getPanel());
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
				Main window = new Main();
				window.frame.setVisible(true);
		});
	}
}
