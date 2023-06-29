package com.Zelda.World;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.Zelda.main.Game;

/* Tile refere-se aos objetos estaticos que fazem parte do mapa como o chao e paredes */
public class Tile {

	/* O static significa que o objeto só será inicializado uma vez*/
	
	public static BufferedImage Tile_Chao   = Game.spritesheet.getsprite(27*8, 27*2, 27, 27);
	public static BufferedImage Tile_Parede = Game.spritesheet.getsprite(27*9, 27*2, 27, 27);
	
	private BufferedImage sprite;
	private int x, y;
	
	
	/* A classe tile recebera as informações de outras classes objetos como Parede e chao e será responsavel por
	 * desenhalas */
	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
	    this.sprite = sprite;
	}

	public void render(Graphics g) {
		g.drawImage(sprite, this.x - Camera.x, this.y - Camera.y, null);
	}
	
}
