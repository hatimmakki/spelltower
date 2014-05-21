package com.me.spelltower.model;

import java.util.Iterator;
import java.util.Stack;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.me.spelltower.screens.GameScreen;
import com.me.spelltower.utils.Assets;
import com.me.spelltower.utils.Tweens;

public class Scena extends Stage {

	public StringBuilder cuvant;
	private boolean eCuvant = false;
	private GameScreen gameScreen;
	
	private float TweenX, TweenY;
	private Array<Float> positionsX;
	private Array<Float> positionsY;

	public static Stack<Actor> stivaActori;
	public Litera matriceLitere[][];

	public Scena( GameScreen gameScreen){
		this.gameScreen = gameScreen;
		stivaActori = new Stack<Actor>();
		cuvant =  new StringBuilder();
		matriceLitere = Assets.getInstance().getMatriceLitere();
		
		positionsX = new Array<Float>();
		positionsY = new Array<Float>();
	}

	@Override
	public boolean touchUp (int screenX, int screenY, int pointer, int button) {

		System.out.println("TOUCHUP!");
		boolean handled =  super.touchUp(screenX, screenY, pointer, button);
		
		Iterator<Actor> iter = stivaActori.iterator();

		Litera litera = null;
		if(eCuvant){
			while(iter.hasNext()){
				litera = (Litera)iter.next();
				litera.setTouchable(Touchable.disabled);
				litera.remove();
				addTweenPositions(litera.getX(), litera.getY());
			}
			
			//calculam positia pentru interpolare, apoi golim listele
			calculateTweenPosition();
			positionsX.clear();
			positionsY.clear();
			
			gameScreen.setFontCoordinates(TweenX, TweenY);
			
			GameScreen.drawTween = true;
			Tweens.tweenPoints(gameScreen.getTweenFont() , TweenX, TweenY + (800-TweenY));
			gameScreen.setWhiteFontColor();
			updateScene();
		}else{
			while(iter.hasNext()){
				litera = (Litera)iter.next();
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

		int lin;
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
					//se iese din ciclu, aigurand o singura iteratie pe fiecare coloana
					break;
				}
			}
		}
	}
	
	private void addTweenPositions(float x, float y){
		positionsX.add(x);
		positionsY.add(y);
	}
	
	private void calculateTweenPosition(){
		float temp = 0;
		
		//calculam media x
		for(Float pos : positionsX){
			temp += pos;
		}
		TweenX = temp/positionsX.size;
		temp = 0;
		
		//calculam media y
		for(Float pos : positionsY){
			temp += pos;
		}
		TweenY = temp/positionsY.size;
	}
	
	public boolean eCuvant(){
		return eCuvant;
	}
	public void setCuvant(boolean var){
		eCuvant = var;
	}
}