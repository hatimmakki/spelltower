package com.me.spelltower;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.me.spelltower.screens.MenuScreen;
import com.me.spelltower.screens.ScoreScreen;
import com.me.spelltower.screens.SplashScreen;
import com.me.spelltower.utils.Assets;

public class SpellTower extends Game {
	
	public static final float WIDTH = 480;
	public static final float HEIGHT = 800;
	public static final String LOG = "SpellTower";
	
	public MenuScreen menuScreen;
	public ScoreScreen scoreScreen;
	
	public SpellTower(){
		super();
	}
	
	@Override
	public void create() {
		Assets.getInstance().load();
		
		menuScreen = new MenuScreen(this);
		scoreScreen = new ScoreScreen(this);
		
		setScreen(menuScreen);
	}
	
	@Override
	public void render() {
		super.render();
		Gdx.input.setCatchBackKey(true);
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void resize (int width, int height) {
		super.resize(width, height);
	}
	
	public Screen getMenuScreen(){
		return menuScreen;
	}
	public Screen getScoreScreen(){
		return scoreScreen;
	}
}
