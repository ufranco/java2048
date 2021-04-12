package com.ufranco.java2048.frontend.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class CargarImagenes {

	
	public static BufferedImage cargarBuffIm(String path) {
		BufferedImage hoja = null;
		
			try {
				hoja = ImageIO.read(new File(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
	
		return hoja;	
	}
	
	public static ImageIcon cargarIcon(String path, int ancho, int alto) {
		
		return new ImageIcon(resize(cargarBuffIm(path), ancho, alto));
	}

	private static Image resize(BufferedImage Bimg, int ancho, int alto) {
		Image scaledImagen = Bimg.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		return scaledImagen;
	}
	
}