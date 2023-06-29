package com.Zelda.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.Zelda.Entitys.Entity;
import com.Zelda.Entitys.Player;
import com.Zelda.Graphycs.Spritesheet;
import com.Zelda.World.World;

public class Game extends Canvas implements Runnable, KeyListener{

	public static int WIDTH = 360, HEIGHT = 200, Scale = 3;
	
	boolean isRunning;
	
	public static Thread thread;
	public static JFrame frame;
	public static BufferedImage layer;
	
	public static List<Entity> entities;
	public static Spritesheet spritesheet;
    public static Player player;
    public static World world;
	
	//Metodo construtor --> roda junto com o main
	public Game() {
		
		//Como temos o canva no projeto é necessario da um add p/ o keylistener funcionar
		addKeyListener(this);
		
		//Criando a janela do jogo
		frame = new JFrame("ZELDA #2");
		//Tamanho da janela
		setPreferredSize(new Dimension(WIDTH * Scale, HEIGHT * Scale));
		InitFrame();
		
		//inicializando objeto - fundo preto layer
		layer = new BufferedImage(WIDTH*1, HEIGHT*1, BufferedImage.TYPE_INT_RGB);
		
		//inicializando objeto - entities do tipo arrayList
		entities = new ArrayList<Entity>();
		//inicializando e indicando onde estão as sprites e inciializando spritesheet
		spritesheet = new Spritesheet("/Spritesheet.png");
		
		//inicializando o player indicando onde queremos ele na tela (0,0) o tamanho (27,27) e a localização da sua sprite
		player = new Player(0, 0, 27, 27, spritesheet.getsprite(0, 0, 27, 27));
		//adicionando o player a entities já que não está adicionado
		entities.add(player);
		
		//inicializando o mundo, sempre depois do spritesheet
		world = new World("/map.png");
		
	}
	
	//Especificações da janela do jogo
	public void InitFrame() {
        // vai adicionar o canvas ao JFrame
        frame.add(this);
        // método para redimencionar a tela, está como falsa para não deixar
        frame.setResizable(false);
        // método do frame para calcular certa as dimensões e mostrar
        frame.pack();
        // método para fazer a localização da tela, dizendo que é null, ela estará indo para o centro
        frame.setLocationRelativeTo(null);
        // método que irá dizer que se clicar no botão de fechar, ele irá fechar, e não deixar em segundo plano
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // método para deixar a janela visível
        frame.setVisible(true);	
		
	}
	
	//main
	public static void main(String[] args) {
		Game game = new Game();
		game.Start();
	}
	
	//Iniciar jogo
	public void Start() {
		isRunning = true;
		Thread thread = new Thread(this);
	    thread.start();	
	}
	
	//Parar jogo
	public void Stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Atualizações do jogo --> logica feita no run
	public void tick() {
		
		//Logica para a atualização de Entity como é um array é necessário percorrela toda
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}
	}
	
	//renderização do jogo --> referente ao grafico
	public void render() {
		
		//Buffer - proporciona um melhor desempenho
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		//fundo preto
		Graphics g = layer.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//Fazendo a renderização do mundo
		world.render(g);
		
		//Logica para a renderização de Entity como é um array é necessário percorrela toda
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
	
		
        g = bs.getDrawGraphics(); 
        g.drawImage(layer, 0, 0, WIDTH * Scale, HEIGHT * Scale, null);
        g.dispose();
        bs.show();
		
	}
	
	//Logica para atualização e renderização do jogo em 60 frames por segundo 
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		while(isRunning){
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000){
				System.out.println("FPS: "+ frames);
				frames = 0;
				timer+=1000;
			}
			
		}
		Stop();
	}
	
	
	//Movimentação do jogador através do KeyEvent
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D ){
			player.Right = true;
			
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A ) {
			player.Left = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W ) {
			player.Up = true;
			
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S ) {
			player.Down = true;
		}
		
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D ){
			player.Right = false;
			
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A ) {
			player.Left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W ) {
			player.Up = false;
			
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S ) {
			player.Down = false;
		}
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
