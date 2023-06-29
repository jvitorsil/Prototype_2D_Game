package com.Zelda.Entitys;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.Zelda.World.Camera;
import com.Zelda.World.World;
import com.Zelda.main.Game;

/*Como o player é uma entidade podemos basicamente dar um extend ao entity e criaremos um metodo construtor no player
No qual tudo que tem na entidade terá agora no player também */

public class Player extends Entity{
	
	private BufferedImage[] RightPlayer;
	private BufferedImage[] LeftPlayer;
	private BufferedImage[] UpPlayer;
	private BufferedImage[] DownPlayer;
	/* Max frames seria a velocidade da animação e maxIndex o numero de sprites nas animações e inMovimento
	 * verifica se o player esta em movimento ou não*/

	public boolean Left, Right, Up, Down, inMovimento = false;
	public int Speed = 1;
	public int dir_Right = 0, dir_Left = 1, dir_Up = 2, dir_Down = 3, frames = 0, maxFrames = 5, index = 0, maxIndex = 4;
	public int dir = dir_Right;
	
	
	/* metodo construtor derivado do entity */
	public Player(int x, int y, int Width, int Height, BufferedImage sprite) {
		/* Super é responsavel por "contruir" oque eu preciso */
		super(x, y, Width, Height, sprite);
	
	    /* fazendo arrays com as sprites do personagem para cada lado que ele se movimenta */
		/* Instanciando as BufferedImage */
		RightPlayer = new BufferedImage[4];
		LeftPlayer  = new BufferedImage[4];
		UpPlayer    = new BufferedImage[4];
		DownPlayer  = new BufferedImage[4];
		
		/* Preenchendo as BufferedImage com as sprites dos personagens */
		for(int i = 0; i < 4; i++) {
			DownPlayer[i]  = Game.spritesheet.getsprite(0 + (i*27), 27*0, 27, 27);
			LeftPlayer[i]  = Game.spritesheet.getsprite(0 + (i*27), 27*1, 27, 27);
			RightPlayer[i] = Game.spritesheet.getsprite(0 + (i*27), 27*2, 27, 27);
			UpPlayer[i]    = Game.spritesheet.getsprite(0 + (i*27), 27*3, 27, 27);
		}
	}

	/* Como a movimentação do jogador está relacionada ao delocamento da imagem através dos eixos, ou seja a cada atualização 
	ele se encontra em uma posição difernete, logo faremos uma lógica no tick para movimenta-lo */
	public void tick() {
		if(Right) {
			inMovimento = true;
			x += Speed;
			dir = dir_Right;
		}else if(Left) {
			inMovimento = true;
			x -= Speed;
			dir = dir_Left;
		}
		
		if(Up) {
			inMovimento = true;
			y -= Speed;
			dir = dir_Up;
		}else if(Down) {
			inMovimento = true;
			y += Speed;
			dir = dir_Down;
		}
		
			/*fazendo a animação do player, no qual de 5 em 5 frames o player muda a sprite animando a utilizamos
			 * o index para a animação*/
			if(inMovimento) {
				frames++;
				if(frames == maxFrames) {
					frames = 0;
					index++;
					if(index == maxIndex) {
						index = 0;
					}
				}
			}
		inMovimento = false;
		
		
		/*A movimentação da camera se da por: 
		 *No qual vamos pegas a posição x e y do personagem e decrementar pelo centro da largura e comprimento 
		 *Da pagina do jogo, apos isso vamos decrementar a camera de todos os objetos que renderizam para dar a 
		 *Impressão de movimento */
		//Camera.x = this.getX() -  (Game.WIDTH/2);
		//Camera.y = this.getY() -  (Game.HEIGHT/2);
	
	    /* Sistema para limitação da camera*/
		Camera.x = Camera.clamp(this.getX() -  (Game.WIDTH/2), 0, World.Width*27 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() -  (Game.HEIGHT/2), 0, World.Height*27 - Game.HEIGHT);
	
	}
	
	
	/* Renderização do player fazendo as animações */
	public void render(Graphics g) {
		
		/* Desenhando a imagem do player, Basicamente definimos os valores X Y WIDTH HEIGHT e o
		 * tamanho da Sprite no GAME instânciando o player e como player é um extends do Entity
		 * os valores são preenchidos no getX e getY e iremos utiliza-lo aqui para desenhar a imagem
		 * -- O dir serve para que o personagem fique naquela posição apos parar de se movimentar 
		 * -- Para o personagem sempre parar em uma posição sem ser andando foi colocado um laço dentro de outro*/
		
		if(dir == dir_Right) {
			if(Right) {
			g.drawImage(RightPlayer[index], this.getX()- Camera.x, this.getY()- Camera.y, null);
			}else if(Right == false) {
				g.drawImage(RightPlayer[0], this.getX()- Camera.x, this.getY()- Camera.y, null);
			}
		}
		
		else if(dir == dir_Left){
			if(Left) {
			g.drawImage(LeftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);	
			}else if(Left == false) {
				g.drawImage(LeftPlayer[1], this.getX() - Camera.x, this.getY()- Camera.y, null);
			}
		}
		
		else if(dir == dir_Up){
			if(Up) {
			g.drawImage(UpPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}else if(Up == false) {
				g.drawImage(UpPlayer[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
		}
		
		else if(dir == dir_Down){
			if(Down) {
			g.drawImage(DownPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);	
			}else if(Down == false) {
				g.drawImage(DownPlayer[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
		}
		
	}
}



/* Se eu não utilizar a renderização aqui no player o proprio player vai ser renderizado pela Entity já que ela 
 * está sendo uma extensao do player, logo para poder anima-lo iremos urilizar a renderização na classe do proprio
 * player*/
