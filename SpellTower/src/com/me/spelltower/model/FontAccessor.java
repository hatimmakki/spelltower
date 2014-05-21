package com.me.spelltower.model;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FontAccessor implements TweenAccessor<BitmapFont_XY>{

	public static final int ALPHA = 0;
	public static final int SCALE = 1;
	public static final int POS_XY = 2;

	@Override
	public int getValues (BitmapFont_XY target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case ALPHA : returnValues[0] = (int)target.getColor().a;

		case SCALE : returnValues[0] = target.getScaleX();
		returnValues[1] = target.getScaleY();
		return 2;
		case POS_XY : returnValues[0] = target.getX(); returnValues[1] = target.getY(); return 2;
		default:
			return -1;
		}
	}

	@Override
	public void setValues (BitmapFont_XY target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case ALPHA : target.setColor(target.getColor().r, target.getColor().g, target.getColor().b, newValues[0]); break;
		case SCALE : target.setScale(newValues[0], newValues[1]); break;
		case POS_XY : target.setX(newValues[0]);
						  target.setY(newValues[1]); 
						  break;
		default:
			break;
		}
	}
}
