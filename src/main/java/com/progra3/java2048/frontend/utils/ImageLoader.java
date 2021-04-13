package com.progra3.java2048.frontend.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import static java.awt.Image.SCALE_SMOOTH;


public class ImageLoader {


	public static BufferedImage loadBufferedImage(String path) {
		BufferedImage bufferedImage = null;

			try {
				bufferedImage = ImageIO.read(new File(path));
			} catch (IOException e) {
				e.printStackTrace();
			}

		return bufferedImage;
	}

	public static ImageIcon loadIcon(String path, int width, int height) {

		return new ImageIcon(resize(loadBufferedImage(path), width, height));
	}

	private static Image resize(BufferedImage bufferedImage, int width, int height) {
		return bufferedImage.getScaledInstance(
			width,
			height,
			SCALE_SMOOTH
		);
	}

}