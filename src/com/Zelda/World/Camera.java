package com.Zelda.World;

public class Camera {

	public static int x;
	public static int y;
	
	/* Tudo que faz renderiza��o � necess�rio decrementar a camera! para que haja a movimenta��o */
	
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
