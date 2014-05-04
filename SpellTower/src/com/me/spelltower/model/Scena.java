package com.me.spelltower.model;

import java.util.Iterator;
import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Scena extends Stage {

	public StringBuilder cuvant;
	public boolean eCuvant = false;

	public static Stack<Actor> stivaActori;

	public Scena(){
		stivaActori = new Stack<Actor>();
		cuvant =  new StringBuilder();
	}

	@Override
	public boolean touchUp (int screenX, int screenY, int pointer, int button) {
		boolean handled =  super.touchUp(screenX, screenY, pointer, button);

		Iterator<Actor> iter = stivaActori.iterator();

		if(eCuvant){
			while(iter.hasNext()){
				Litera litera = (Litera)iter.next();
				litera.setVisible(false);
				litera.remove();
			}
		}else{
			while(iter.hasNext()){
				Litera litera = (Litera)iter.next();
				litera.setColour(Litera.Color.Blue);
				litera.setTouchable(Touchable.enabled);
			}

			/*while(iter.hasNext()){
				Litera litera = (Litera)iter.next();


				if(litera.getColour() == Litera.Color.Green){
					//litera.addAction(Actions.actuine());
					//litera.act(Gdx.graphics.getDeltaTime());

					SpriteBatch batch = (SpriteBatch)getSpriteBatch();

					batch.begin();
					litera.draw(getSpriteBatch(), 0);
					batch.end();
					litera.remove();
				}
				else{
					litera.setColour(Litera.Color.Blue);
					litera.setTouchable(Touchable.enabled);
				}
			}*/

		}
		stivaActori.removeAllElements();
		cuvant.delete(0, cuvant.length());
		eCuvant = false;
		return handled;


		/*@Override
	public boolean touchDragged (int screenX, int screenY, int pointer) {
		boolean handled = super.touchDragged(screenX, screenY, pointer);

		//verificam daca e cuvant
		eCuvant = Assets.getInstance().eCuvant(cuvant.toString());

		Vector2 vect = new Vector2(screenX,screenY);
		screenToStageCoordinates(vect);


		Litera actor = (Litera) super.hit((float)vect.x, (float)vect.y, true);
		if (actor != null) {
			if (actor instanceof Litera) {
				//evitam adaugarea aseiasi litere
				if(actor.hashCode() != lastHashCode){
					cuvant.append(actor.getLitera());
					lastHashCode = actor.hashCode();
				}

				flaggedActors.add(actor);
				Litera myActor = (Litera) actor;
				myActor.touchOver(vect.x, vect.y, pointer);
			}           
		}

		//System.out.println("DRAGGED");
		return handled;
	} */
	}
}