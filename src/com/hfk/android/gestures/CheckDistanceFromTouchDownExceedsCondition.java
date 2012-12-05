package com.hfk.android.gestures;

import java.util.List;

import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

import com.hfk.android.ScreenVector;

public class CheckDistanceFromTouchDownExceedsCondition extends GestureConditionBase<View> {

	public CheckDistanceFromTouchDownExceedsCondition(View view) {
		super(view);
	}
	
	@Override
	public boolean checkCondition(GestureEvent motion, TouchGesture gesture) {
		ScreenVector actionDown = (ScreenVector)gesture.getContext("ACTION_DOWN");
		if(actionDown != null)
		{
			return (int)(actionDown.distance(motion.getPosition())) > 10;
		}

		return true;
	}
}