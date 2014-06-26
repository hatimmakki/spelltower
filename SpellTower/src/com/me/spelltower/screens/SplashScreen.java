package com.me.spelltower.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.spelltower.SpellTower;
import com.me.spelltower.model.SpriteAccessor;

public class SplashScreen implements Screen {
	
	private Sprite splash;
	private Texture texture;
	private TweenManager manager;
	private SpriteBatch batch;
	private SpellTower game;
	
	public SplashScreen(SpellTower game){
		this.game = game;
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		manager.update(delta);
		
		batch.begin();
		splash.draw(batch);
		batch.end();
		
	}

	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void show () {
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		manager = new TweenManager();
		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("temp/splashscreen.png"));
		splash = new Sprite(texture);
		splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		splash.setAlpha(0);
		
		Timeline.createSequence()
			//.push(Tween.set(splash, SpriteAccessor.ALPHA).target(0))
			.push(Tween.to(splash, SpriteAccessor.ALPHA, 3f).target(1)).delay(2f)
			.push(Tween.to(splash, SpriteAccessor.ALPHA, 2f).target(0))
			.setCallback(new TweenCallback() {
				
				@Override
				public void onEvent (int type, BaseTween<?> source) {
					game.setScreen(game.getMenuScreen());
					
				}
			})
			.start(manager);
		
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
