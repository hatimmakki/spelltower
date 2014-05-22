package com.me.spelltower.model;

import java.util.Iterator;
import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.me.spelltower.screens.GameScreen;
import com.me.spelltower.utils.Assets;
import com.me.spelltower.utils.Tweens;

public class Scena extends Stage implements InputProcessor{

	private StringBuilder cuvant;
	private int points;
	private boolean eCuvant = false;
	private GameScreen gameScreen;

	private float TweenX, TweenY;
	private Array<Float> positionsX;
	private Array<Float> positionsY;

	private  Stack<Actor> stivaActori;
	public Litera matriceLitere[][];

	private int lastHitActorX = -1;
	private int lastHitActorY = -1; 

	public Scena( GameScreen gameScreen){
		this.gameScreen = gameScreen;
		stivaActori = new Stack<Actor>();
		cuvant =  new StringBuilder("");
		matriceLitere = Assets.getInstance().getMatriceLitere();

		positionsX = new Array<Float>();
		positionsY = new Array<Float>();
	}
	
	@Override
	public void draw () {
		gameScreen.getTweenManager().update(Gdx.graphics.getDeltaTime());
		super.draw();
	}

	@Override
	public boolean touchUp (int screenX, int screenY, int pointer, int button) {

		//System.out.println("TOUCHUP!");
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

			//calculam punctele
			gameScreen.setPoints( calculatePoints(stivaActori.size()) );

			GameScreen.drawTween = true;
			Tweens.tweenPoints(gameScreen.getTweenFont() , TweenX, TweenY + (800-TweenY));

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
		gameScreen.setWhiteFontColor();
		resetLastHitActor();
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

	public int calculatePoints(int stackSize){
		if(stackSize == 3){
			return 3;
		}
		else if(stackSize > 3){
			return (stackSize - 3) * 2 + 3 ;
		}
		return -1;
	}

	public boolean eCuvant(){
		return eCuvant;
	}
	public void setCuvant(boolean var){
		eCuvant = var;
	}

	public void pushActor(Litera litera){
		stivaActori.add(litera);
	}
	public Stack<Actor> getStivaActori(){
		return stivaActori;
	}

	public void AppendToWord(String string){
		cuvant.append(string);
	}
	public String getCuvant(){
		return cuvant.toString();
	}

	public void setLastHitActorCoord(int x, int y){
		lastHitActorX = x;
		lastHitActorY = y;
	}

	public int getLastHitActorX(){
		return lastHitActorX;
	}

	public int getLastHitActorY(){
		return lastHitActorY;
	}
	public void resetLastHitActor(){
		lastHitActorX = -1;
		lastHitActorY = -1;
	}
}