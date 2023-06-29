package com.Zelda.World;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.Zelda.Entitys.Armas;
import com.Zelda.Entitys.Entity;
import com.Zelda.Entitys.Inimigo;
import com.Zelda.Entitys.Lives;
import com.Zelda.Entitys.Municao;
import com.Zelda.main.Game;

public class World {

	private Tile[] tiles;
	public static int Width;
	public static int Height;
	
	/* A String path faz mensão ao arquivo png do mapa "/map.png" iniciado na classe Game */
	public World(String path) {
		try {
			/* Definindo o mapa */
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			
			/* preenchendo as variaveis com os valores com comprimento e largura do map.png */
			Width = map.getWidth();
			Height = map.getHeight();
			
			/*fazendo a leitura dos pixeis da imagem verificando seu tamanho atraves de um vetor/array
			 * multiplicando a sua altura pela largura */
			int[] pixel = new int[map.getWidth() * map.getHeight()];
			
			tiles = new Tile[map.getWidth() * map.getHeight()];
			
			/* Preenchendo os pixeis do map na array que criamos, pegando todas as dimensões do mapa e convertendo
			 * em um array e gravando em pixel*/
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixel, 0, map.getWidth());
			
			/*Criando um laço para percorrer a array pixel, torna possivel fazer a leitura de pixel. Fando isso será 
			 * possivel verificar a posição de cores diferentes e colocar objetos nessas posições*/
			
			for(int xx = 0; xx < map.getWidth(); xx++) {
				for(int yy = 0; yy < map.getHeight(); yy++) {
					
					tiles[xx + (yy * Width)] = new Chao(xx*27, yy*27, Tile.Tile_Chao);
					
					int PosicaoAtual = pixel[xx + (yy * map.getWidth())];
					
					if(PosicaoAtual == 0xFF000000) {
						//Cor Preta - Chao
						tiles[xx + (yy * Width)] = new Chao(xx*27, yy*27, Tile.Tile_Chao);
						
					}else if(PosicaoAtual == 0xFFFFFFFF) {
						//Cor Branca - Paredes
						tiles[xx + (yy * Width)] = new Parede(xx*27, yy*27, Tile.Tile_Parede);
						
					}else if(PosicaoAtual == 0xFF0000FF){
						//Player
						System.out.println("eae nro");
						Game.player.setX(xx * 27);
						Game.player.setY(yy * 27);
						
                    }else if(PosicaoAtual == 0xFFFF6A00){
						//Arma
                    	Game.entities.add(new Armas(xx*27, yy*27, 27, 27, Entity.Entity_Arma));
     
					}else if(PosicaoAtual == 0xFFFFD800){
						//Munição
						Game.entities.add(new Municao(xx*27, yy*27, 27,27, Entity.Entity_Municao));
						
					}else if(PosicaoAtual == 0xFFFF006E){
						//Vida
						Game.entities.add(new Lives(xx*27, yy*27, 27, 27, Entity.Entity_Vida));
						
					}else if(PosicaoAtual == 0xFFFF0000){
						/* Inimigo adicionamos o inimigo a classe de entidades e indicamos sua localização no mapa
						 * Como temos um array que esta varrendo o map multiplicamos ele pela escala do nosso mapa
						 * e indicamos a */
						Game.entities.add(new Inimigo(xx*27, yy*27, 27, 27, Entity.Entity_Inimigo));
					}else {
						//Quando não bater com nenhuma condição será chao
						tiles[xx + (yy * Width)] = new Chao(xx*27, yy*27, Tile.Tile_Chao);
						
					}
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	
	/* Fazendo a renderização do mundo */
	public void render(Graphics g) {
		
		/* Metodo para renderizar somente oque está nos limites da camera
		 * -- xStart e yStart são as posiçoes onde se inicia a camera dividido pelo tamanho da Tile (27 pixel)*/
		int xStart = Camera.x/27;
		int yStart = Camera.y/27;
		
		/* Agora o xFinal e a posição inicial mais o quanto cabe dentro da camera que é a tamanho ou a altura
		 * dividito pelo tamanho da map 27 */
		int xFinal = xStart + (Game.WIDTH/27);
	    int yFinal = yStart + (Game.HEIGHT/27);
	    /*Apos isso basta substituir abaixo no lugar do 0 e width e height*/
		
		//Fazendo a renderização do Tile percorrendo todo seu array
		for(int xx = 0; xx < Width; xx++) {
			for(int yy = 0; yy < Height; yy++) {
				
				Tile tile = tiles[xx + (yy * Width)];
				tile.render(g);
			}
		}
	}
	
	
}
