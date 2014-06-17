package com.me.spelltower.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MenuScreen implements Screen{
	
	private Button start;
	private Stage stage;
	private ButtonStyle style;
	private TextureRegionDrawable drawable;
	
	@Override
	public void show () {
		start = new Button();
		stage = new Stage();
		style = new ButtonStyle();
		
		
		//style.up = Gdx.files.internal("ui/button1.png").
		//style.down = (Drawable)Gdx.files.internal("ui/button2.png");
		
		//start.setStyle(style);
		//start.setPosition(100, 400);
		
		//stage.addActor(start);
		
	}
	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.draw();
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
