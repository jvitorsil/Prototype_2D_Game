package com.Zelda.Graphycs;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {

	private BufferedImage spritesheet;
	
	public Spritesheet(String path) {
		
		//Leitura de uma imagem
		try {
			spritesheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getsprite(int x, int y, int Width, int Height) {
		return spritesheet.getSubimage(x, y, Width, Height);
	}
	
	
}
