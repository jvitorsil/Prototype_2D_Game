package com.Zelda.Entitys;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.Zelda.World.Camera;
import com.Zelda.main.Game;

public class Entity {
	
	//Iniciando as entidades -- mesma logica do Tiles
	public static BufferedImage Entity_Vida    = Game.spritesheet.getsprite(27*9, 27*1, 27, 27);
	public static BufferedImage Entity_Arma    = Game.spritesheet.getsprite(27*8, 27*0, 27, 27);
	public static BufferedImage Entity_Municao = Game.spritesheet.getsprite(27*9, 27*0, 27, 27);
	public static BufferedImage Entity_Inimigo = Game.spritesheet.getsprite(27*4, 27*0, 27, 27);
	
	
	//Por padrão toda entity terá 
	protected int x, y, Width, Height;
    public BufferedImage sprite;
	

	//Agora quando formos criar uma entity precisamos passar todos os construtores:
	public Entity(int x, int y, int Width, int Height, BufferedImage sprite) {
		
		//O this serve para especificar que a variavel que queremos pegar é a da classe e não a do metodo
		this.x = x;
		this.y = y;
		this.Width = Width;
		this.Height = Height;
		this.sprite = sprite;
	}
	
	
    //o set quer dizer que irá informar novamento o valor da variavel, alterando-a, ou seja, quando chamdo ele irá informar 
    //o valor da variavel
    
    public void setX(int newX) {
    	this.x = newX;         }
    
    public void setY(int newY) {
    	this.y = newY;         }
    
	//Apos as variaveis preenchidas iremos acessalas agora através de metodos 
	//o get quer dizer que iremos obter esse valor, ele irá informalo
    public int getX()  {
    	return this.x; }
    
    public int getY()  {
    	return this.y; }
    
    public int getWidth()  {
    	return this.Width; }
    
    public int getHeight()  {
    	return this.Height; }
    
    
    public void tick() {}
    
    //Agora iremos renderizar a imagem informada por todos os dados obtidos a cima
    //Graphics g é responsavel por desenhar a imagem
    public void render(Graphics g) {
    	g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);
    }
}
