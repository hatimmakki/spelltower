package com.me.spelltower.utils;

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
		.push(Tween.to(font, FontAccessor.POS_XY, 1.5f).target(x, y))
		.push(Tween.to(font, FontAccessor.ALPHA, 1.5f).target(0))
		.start(GameScreen.getTweenManager())
		.setCallback(new TweenCallback() {
			@Override
			public void onEvent (int type, BaseTween<?> source) {
				GameScreen.drawTween = false;
			}
		});
	}

	public static void TweenLiteraToY(Litera litera, float y){
		Tween.to(litera, LiteraAccessor.POSITION_Y, 2000f)
		.target(y)
		.ease(Bounce.OUT)
		.start(GameScreen.getTweenManager());
	}
	
	public static void TweenLiteraFromY(Litera litera, float y){
		Tween.from(litera, LiteraAccessor.POSITION_Y, 1000f)
		.target(y)
		.ease(Bounce.OUT)
		.start(GameScreen.getTweenManager());
	}
}