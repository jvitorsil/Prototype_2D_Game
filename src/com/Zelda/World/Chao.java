package com.Zelda.World;

import java.awt.image.BufferedImage;

public class Chao extends Tile{

	
	/* � um extends do tile, ou seja ao chamar-mos o metodo construtor abaixo ele ir� para o metodo construtor do Tile
	 * que ser� responsavel por desenhar a imagem */
	public Chao(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
	}

}
