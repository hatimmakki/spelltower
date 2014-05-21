package com.me.spelltower;

import com.badlogic.gdx.Game;
import com.me.spelltower.screens.GameScreen;
import com.me.spelltower.screens.SplashScreen;
import com.me.spelltower.utils.Assets;

public class SpellTower extends Game {
	
	public static final float WIDTH = 480;
	public static final float HEIGHT = 800;
	public static final String LOG = "SpellTower";
	
	public SpellTower(){
		super();
	}
	
	@Override
	public void create() {
		Assets.getInstance().load();
		setScreen(new GameScreen());
	}
	
	@Override
	public void render() {
		super.render();
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void resize (int width, int height) {
		super.resize(width, height);
	}
}
