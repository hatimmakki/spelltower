package com.me.spelltower.utils;

import java.util.Random;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Bounce;

import com.me.spelltower.model.BitmapFont_XY;
import com.me.spelltower.model.FontAccessor;
import com.me.spelltower.model.Litera;
import com.me.spelltower.model.LiteraAccessor;
import com.me.spelltower.screens.GameScreen;

public class Tweens {

	public static void tweenPoints(BitmapFont_XY font, float x, float y){
		Timeline.createParallel()
		.push(Tween.to(font, FontAccessor.POS_XY, 5.9f).target(x, y))
		.push(Tween.to(font, FontAccessor.ALPHA, 5.9f).target(0))
		.start(GameScreen.getTweenManager())
		.setCallback(new TweenCallback() {
			@Override
			public void onEvent (int type, BaseTween<?> source) {
				GameScreen.drawTween = false;
			}
		});
	}

	public static void TweenLiteraToY(Litera actor, float y){
		Tween.to(actor, LiteraAccessor.POS_Y, 2f)
			.target(y)
			.ease(Bounce.OUT)
		.start(GameScreen.getTweenManager());
	}
	
	public static void throwActor( final Litera actor){
		
		int offset = 50;
		if(new Random().nextBoolean()){
			offset = -50;
		}
			
		Timeline.createParallel()
			.push(Tween.to(actor, LiteraAccessor.ROTATION, 1.0f)
					.target(360*3.7f))
					
			.push(Tween.to(actor, LiteraAccessor.POS_XY, 2.8f)
				.waypoint(actor.getX() + offset, actor.getY()+200)
				.target(actor.getX(), -100)
				.setCallback(new TweenCallback() {
					
					@Override
					public void onEvent (int type, BaseTween<?> source) {
						actor.remove();
					}
				}))
			.start(GameScreen.getTweenManager());
	}
}