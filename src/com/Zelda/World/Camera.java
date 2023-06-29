package com.Zelda.World;

public class Camera {

	public static int x;
	public static int y;
	
	/* Tudo que faz renderização é necessário decrementar a camera! para que haja a movimentação */
	
	/* Sistema para limitar a camera dentro da area do jogo*/

    public static int clamp(int Atual, int Min, int Maximo) {
    	if(Atual < Min){
    		Atual = Min;
    		
    	}   
    	if(Atual > Maximo) {
    		Atual = Maximo;
    	}
        return Atual;
    }



}
