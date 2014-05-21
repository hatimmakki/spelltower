package com.me.spelltower.model;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class BitmapFont_XY extends BitmapFont{
	
	private float x,y;
	
	public BitmapFont_XY(FileHandle font, float x, float y){
		
		super(font);
		this.x = x;
		this.y = y;
	}

	public float getX () {
		return x;
	}

	public void setX (float x) {
		this.x = x;
	}

	public float getY () {
		return y;
	}

	public void setY (float y) {
		this.y = y;
	}

}
