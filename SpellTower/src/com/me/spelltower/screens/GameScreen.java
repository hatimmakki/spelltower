package com.me.spelltower.screens;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.me.spelltower.model.BitmapFont_XY;
import com.me.spelltower.model.Litera;
import com.me.spelltower.model.Scena;
import com.me.spelltower.utils.Assets;

public class GameScreen implements Screen{

	private StretchViewport viewPort;
	private OrthographicCamera camera;
	private Scena stage;
	private SpriteBatch batch;
	private BitmapFont_XY font;
	private static BitmapFont_XY tweenFont;
	private FPSLogger logger;
	private static TweenManager manager;
	private  Litera matriceLitere[][];
	private ShapeRenderer shapeRend;
	
	public static boolean drawTween = false;
	private int points;
	private int totalPoints = 0;
	private String totalPointsStr = "0";

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		logger.log();

		stage.draw();
		stage.act();

		batch.begin();
		
		font.draw(batch, stage.getCuvant().toUpperCase() , 15, 790); 
		font.draw(batch, totalPointsStr, 420, 790); 

		if(stage.eCuvant()){
			font.setColor(Color.GREEN);
		}

		if(drawTween){
			tweenFont.draw(batch, "+" + points, tweenFont.getX(), tweenFont.getY());
		}
		
		manager.update(delta);
		
		batch.end();
		
		//draw the line
		shapeRend.begin(ShapeType.Line);
		shapeRend.setColor(Color.ORANGE);
		shapeRend.line(0, 745, 480, 745);
		shapeRend.end();
	}

	@Override
	public void show () {

		logger = new FPSLogger();
		batch = new SpriteBatch();


		font = Assets.getInstance().getFont();
		tweenFont = Assets.getInstance().getFont();
		tweenFont.setColor(Color.GREEN);
		tweenFont.setX(300);
		tweenFont.setY(780);

		manager = new TweenManager();
		stage = new Scena(this);
		matriceLitere = Assets.getInstance().getMatriceLitere();

		camera = new OrthographicCamera();
		camera.position.set(480/2, 800/2, 0);
		camera.update();

		shapeRend = new ShapeRenderer();

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

	public void setWhiteFontColor(){
		font.setColor(Color.WHITE);
	}

	public BitmapFont_XY getFont(){
		return font;
	}

	public static TweenManager getTweenManager(){
		return manager;
	}
	public void setFontCoordinates(float x, float y){
		tweenFont.setX(x);
		tweenFont.setY(y);
	}
	public BitmapFont_XY getTweenFont(){
		return tweenFont;
	}
	
	public void setPoints(int points){
		this.points = points;
		totalPoints += points;
		totalPointsStr = totalPoints + "";
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
