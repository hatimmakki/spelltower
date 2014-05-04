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

public class GameScreen implements Screen{

	private StretchViewport viewPort;
	private OrthographicCamera camera;
	private Scena stage;
	private BitmapFont font;
	private SpriteBatch batch;
	private ArrayList<String> alfabetGenerat;
	private FPSLogger logger;
	private Litera matriceLitere[][];

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
		matriceLitere = new Litera[11][7];

		camera = new OrthographicCamera();
		camera.position.set(480/2, 800/2, 0);
		camera.update();

		viewPort = new StretchViewport(480, 800);
		viewPort.setCamera(camera);
		stage.setViewport(viewPort);

		genereazaAlfabet();
		genereazaMatricea();

		for(int i = 0; i<11; i++){
			for(int j = 0; j<7; j++){
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

	private void genereazaAlfabet(){
		alfabetGenerat = new ArrayList<String>();
		int[] frecventaCumulativa = SpellTowerGame.frecventaCumulativa;


		for(int i = 0; i<77;i++){
			int rand = new Random().nextInt(647897);

			int index = Arrays.binarySearch(frecventaCumulativa, rand);

			System.out.println(index);
			alfabetGenerat.add(SpellTowerGame.alfabet[Math.abs(index)-1]);

			Collections.shuffle(alfabetGenerat);
		}
	}

	private void genereazaMatricea(){

		int indexAlfabet = 0;
		int pozCol = 2;
		
		for(int i = 0; i<11; i++){
			
			int pozLinie = 2;
			
			for(int j = 0; j<7; j++){
				Litera t = new Litera(alfabetGenerat.get(indexAlfabet), pozLinie, pozCol);
				matriceLitere[i][j] = t;
				pozLinie += 67;
				indexAlfabet++;
			}
			pozCol += 67;
		}
	}


}
