package com.me.spelltower.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.me.spelltower.SpellTower;
import com.me.spelltower.utils.Assets;

public class MenuScreen implements Screen{
	
	private TextButton start;
	private TextButton exit;
	private TextButton setari;
	private TextButton scor;
	private TextButtonStyle buttonStyle;
	private BitmapFont font;
	private Table table;
	
	private OrthographicCamera camera;
	private Stage stage;
	private Viewport viewport;
	private SpellTower game;
	
	public MenuScreen(final SpellTower game){
		
		this.game = game;
		stage = new Stage();
		table = new Table();
		Gdx.input.setInputProcessor(stage);
		camera = new OrthographicCamera(SpellTower.WIDTH, SpellTower.HEIGHT);
		camera.position.set(SpellTower.WIDTH/2, SpellTower.HEIGHT/2, 0);
		viewport = new StretchViewport(SpellTower.WIDTH, SpellTower.HEIGHT);
		viewport.setCamera(camera);
		stage.setViewport(viewport);
		camera.update();
		
		buttonStyle = Assets.getInstance().getButtonStyle();
		
		buttonStyle.font.setScale(0.7f);
		
		start = new TextButton("START", buttonStyle);
		start.addListener(new ChangeListener() {
			
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				game.setScreen(new GameScreen());
			}
		});
		exit = new TextButton("EXIT", buttonStyle);
		exit.addListener(new ChangeListener() {
			
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				Gdx.app.exit();
			}
		});
		
		setari = new TextButton("SETARI", buttonStyle);
		
		scor = new TextButton("SCOR", buttonStyle);
		scor.addListener(new ChangeListener() {
			
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				game.setScreen(new ScoreScreen(game));
			}
		});
		
		table.setFillParent(true);
		
		table.row();
		table.add(start).center().padBottom(50).size(300, 100).setWidgetX(500);;
		table.row();
		table.add(scor).center().padBottom(50).size(300, 100);
		table.row();
		table.add(setari).center().padBottom(50).size(300, 100);
		table.row();
		table.add(exit).center().padBottom(50).size(300, 100);
		
		stage.addActor(table);
		
	}
	
	
	@Override
	public void show () {
		
	}
	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(30 /100f, 167/100f, 225/100f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.draw();
		table.debug();
	}

	@Override
	public void resize (int width, int height) {
		stage.getViewport().update(width, height);
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
