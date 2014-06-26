package com.me.spelltower.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.spelltower.SpellTower;
import com.me.spelltower.utils.Assets;

public class ScoreScreen implements Screen{
	
	private ArrayList<Integer> listaScoruri;
	private BitmapFont font;
	private SpriteBatch batch;
	
	public ScoreScreen(final SpellTower game){
		this.game = game;
		batch = new SpriteBatch();
		font = Assets.getInstance().getUIFontWhite();
		font.setColor(Color.BLACK);
	}

	private SpellTower game;
	
	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(30 /100f, 167/100f, 225/100f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (Gdx.input.isKeyPressed(Keys.BACK)){
			  game.setScreen(game.getMenuScreen());
			}
		
		batch.begin();
		font.setScale(0.5f);
		
		font.draw(batch, "SCOR MAXIM", 50, 700);
		
		int pozY = 600;
		int poz = 1;
		
		
		font.setColor(Color.GRAY);
		for(int i = 5; i != 0 ; i--){
			font.draw(batch, poz +".  " + listaScoruri.get(i-1).toString(), 100, pozY);
			pozY = pozY - 100;
			poz++;
		}
		batch.end();
	}


	@Override
	public void show () {
		listaScoruri = Assets.getInstance().getListaScoruri();
	}

	@Override
	public void resize (int width, int height) {
	}
	@Override
	public void hide () {
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
	}
}
