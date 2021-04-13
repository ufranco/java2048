package com.progra3.java2048.frontend.mainView;

import com.progra3.java2048.frontend.utils.ImageLoader;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;

public class Board {
	private final ArrayList<JLabel> tiles = new ArrayList<>();
	private final Icon tile = ImageLoader.loadIcon("res\\tile.png", 128, 128);
	private final Icon emptyTile = ImageLoader.loadIcon("res\\tile0.png", 128, 128);

	Board(JPanel panel, Integer[][] board){
		fillArray(board);
		createBoard(panel);
	}

	private void fillArray(Integer[][] board) {
		int count = 0;

			for (Integer[] integers : board) {
			for (Integer integer : integers) {
				tiles.add(new JLabel(integer == 0 ? "" : integer.toString()));

				var label = tiles.get(count);
				label.setIcon(emptyTile);
				label.setOpaque(true);
				label.setForeground(new Color(255, 255, 0));
				label.setBorder(new MatteBorder(2, 2, 2, 2, new Color(0, 0, 0)));
				label.setFont(new Font("Verdana", Font.BOLD, 30));
				label.setHorizontalTextPosition(JLabel.CENTER);
				label.setVerticalTextPosition(JLabel.CENTER);
				count++;
			}
		}
	}
	private void createBoard(JPanel panel) {
			tiles.forEach(panel::add);
	}

	public void updateBoard(Integer[][] boardValues, JPanel panel) {
		int count= 0;
		for (Integer[] boardValue : boardValues) {
			for (Integer value : boardValue) {
				var label = (JLabel) panel.getComponent(count);
				changeTile(label, value);
				count++;
			}
		}
	}
	public void changeTile(JLabel label, Integer value) {
		if (!label.getText().equals(value.toString())) {
			if(value == 0) {
				label.setText("");
				label.setIcon(ImageLoader.loadIcon("res\\tile0.png", 128, 128));
			} else {
				label.setText(value.toString());
				label.setIcon(tile);
			}
		}
	}

}