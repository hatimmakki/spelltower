package com.me.spelltower.model;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;


public class Actions {
	

	public static Action actuine(){
		Action action = sequence(moveBy(10, 30), scaleBy(2, 2, 3, Interpolation.bounce),delay(2));
		return action;
	}
}
