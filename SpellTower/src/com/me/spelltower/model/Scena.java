package com.me.spelltower.model;

import java.util.Iterator;
import java.util.Stack;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.me.spelltower.utils.Assets;

public class Scena extends Stage {


	public StringBuilder cuvant;
	public boolean eCuvant = false;

	public static Stack<Actor> stivaActori;
	public Litera matriceLitere[][];

	public Scena(){
		stivaActori = new Stack<Actor>();
		cuvant =  new StringBuilder();
		matriceLitere = Assets.getInstance().getMatriceLitere();
	}

	@Override
	public boolean touchUp (int screenX, int screenY, int pointer, int button) {

		System.out.println("TOUCHUP!");
		boolean handled =  super.touchUp(screenX, screenY, pointer, button);

		Iterator<Actor> iter = stivaActori.iterator();

		if(eCuvant){
			while(iter.hasNext()){
				Litera litera = (Litera)iter.next();
				litera.setTouchable(Touchable.disabled);
				litera.remove();
			}
			updateScene();
		}else{
			while(iter.hasNext()){
				Litera litera = (Litera)iter.next();
				litera.setColour(Litera.Color.Blue);
				litera.setTouchable(Touchable.enabled);
			}
		}

		stivaActori.removeAllElements();
		cuvant.delete(0, cuvant.length());
		eCuvant = false;
		return handled;
	}

	private void updateScene(){

		int lin, col;
		Litera literaPreced;
		for(int j = 6; j >= 0; j--){
			for(int i = 10; i >= 0; i--){

				if(!matriceLitere[i][j].isTouchable()){

					lin = i;

					for(int k = 1; k <= i; k++){

						Litera litera = matriceLitere[i-k][j];

						if(litera.isTouchable()){
							System.out.println("s-a mutat " + litera.getLitera() + " pe linia "+lin);
							litera.setLinie(lin);
							lin--;

						}
					}
					//se iese din ciclu, aigurand o singura iteratie pe fiecar coloana
					break;
				}
			}
		}
	}
}