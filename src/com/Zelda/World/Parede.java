package com.Zelda.World;

import java.awt.image.BufferedImage;

public class Parede extends Tile{

	
	
	/* É um extends do tile, ou seja ao chamar-mos o metodo construtor abaixo ele irá para o metodo construtor do Tile
	 * que será responsavel por desenhar a imagem */
	
	public Parede(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
	}

}
