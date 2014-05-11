package com.me.spelltower.screens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.me.spelltower.model.Litera;
import com.me.spelltower.model.Scena;
import com.me.spelltower.model.SpellTowerGame;
import com.me.spelltower.utils.Assets;

public class GameScreen implements Screen{

	private StretchViewport viewPort;
	private OrthographicCamera camera;
	private Scena stage;
	private BitmapFont font;
	private SpriteBatch batch;
	private FPSLogger logger;
	public Litera matriceLitere[][];
	

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//logger.log();

		batch.begin();
		font.draw(batch, stage.cuvant , 50, 790);
		if(stage.eCuvant){
			font.draw(batch, " E cuvant!", 100, 790);
		}
		batch.end();
		stage.draw();
		stage.act();
	}

	@Override
	public void show () {

		logger = new FPSLogger();
		batch = new SpriteBatch();
		font = new BitmapFont();
		stage = new Scena();
		matriceLitere = Assets.getInstance().getMatriceLitere();

		camera = new OrthographicCamera();
		camera.position.set(480/2, 800/2, 0);
		camera.update();

		viewPort = new StretchViewport(480, 800);
		viewPort.setCamera(camera);
		stage.setViewport(viewPort);

		for(int i =10; i>=0; i--){
			for(int j = 0; j <7; j++){
				stage.addActor(matriceLitere[i][j]);
			}
		}

		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void resize (int width, int height) {
		stage.getViewport().update(width, height, false);
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
