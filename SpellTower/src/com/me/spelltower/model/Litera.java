package com.me.spelltower.model;

import java.util.Iterator;
import java.util.Random;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Bounce;

import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.me.spelltower.utils.Assets;

public class Litera extends Actor{
	private String litera;
	private TextureRegion textura;
	private TextureRegion[] textures;
	private final int WIDTH = 64, HEIGHT = 64;
	private Color currentColor;
	private TweenManager manager;
	private Scena stage;


	int temp = 0;

	public Litera(String litera, float x, float y, int linie, int coloana){

		this.litera = litera;
		textures = Assets.getInstance().getRegions(litera);
		textura = textures[0];
		textura.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		currentColor = Color.Blue;
		manager = new TweenManager();
		stage = (Scena)getStage();


		setTouchable(Touchable.enabled);
		setBounds(x, y, 50, 50);
	}



	public void draw(Batch batch, float parentAlpha) {
		batch.draw(textura, getX(), getY(), WIDTH, HEIGHT);
		manager.update(parentAlpha);
	}

	@Override
	public Actor hit (float x, float y, boolean touchable) {
		Litera actor = (Litera)super.hit(x, y, touchable);
		
		stage = (Scena)getStage();

		if(actor == this)
			System.out.println("ACTOR HIT LITERA " + litera +"   "+ temp++ );

		if(actor != null && actor == this){


			System.out.println(Math.abs((actor.getX() - stage.getLastHitActorX())));
			System.out.println(Math.abs((actor.getY() - stage.getLastHitActorY())));

			if(stage.getLastHitActorX() != -1){
				if( (Math.abs((actor.getX() - stage.getLastHitActorX())) > 134) || (Math.abs((actor.getY() - stage.getLastHitActorY())) > 134) ){
					return null;
				}
			}

			stage.setLastHitActorCoord((int)actor.getX(), (int)actor.getY());
			

			actor.setTouchable(Touchable.disabled);

			//Adaugam litera 
			stage.AppendToWord(this.litera);

			//Schimbam culoarea
			actor.setColour(Litera.Color.Yellow);

			stage.pushActor(actor);

			Iterator iter = stage.getStivaActori().iterator();

			//verificam daca e cuvant
			if(Assets.getInstance().eCuvant(stage.getCuvant())){

				//In caz pozitiv, schimbam culoarea tuturor literelor cuvantului
				stage.setCuvant(true);
				while(iter.hasNext()){
					Litera temp = (Litera)iter.next();
					temp.setColour(Color.Green);
					temp.setTouchable(Touchable.disabled);
				}
			}
			else{
				stage.setCuvant(false);;

				while(iter.hasNext()){
					((Litera)(iter.next())).setColour(Litera.Color.Yellow);
				}
			}
		}
		return actor;
	}

	public void setLinie (int linie) {

		Tween.to(this, LiteraAccessor.POSITION_Y, 80f)
		.target(calculeazaY(linie))
		.ease(Bounce.OUT)
		.start(manager);
	}

	public static enum Color{
		Blue, Yellow, Green;
	}

	public void setColour(Color color){
		switch(color){
		case Blue : this.textura = textures[0];
		currentColor = Color.Blue;
		break;
		case Yellow : this.textura = textures[1];
		currentColor = Color.Yellow;
		break;
		case Green : this.textura = textures[2];
		currentColor = Color.Green;
		break;
		}
	}

	public Color getColour(){
		return currentColor;
	}

	public String getLitera(){
		return litera;
	}

	private float calculeazaY(int pozitie){
		if(pozitie == 10){
			return 2f;
		}
		else{
			return ((10 - pozitie) * 67) +2;
		}
	}
	
	public void resetLastHitActor(){
		 
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = new Random().nextInt(1000);
		result = prime * result + ((litera == null) ? 0 : litera.hashCode());
		return result;
	}

	@Override
	public boolean equals (Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Litera other = (Litera)obj;
		if (litera == null) {
			if (other.litera != null) return false;
		} else if (!litera.equals(other.litera)) return false;
		return true;
	}
}