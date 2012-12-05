package com.hfk.android.gestures;

import android.view.View;

import com.hfk.android.ScreenVector;

public class CheckDistanceFromRegisteredPointExceedsCondition extends GestureConditionBase<View> {

	public CheckDistanceFromRegisteredPointExceedsCondition(View view, String key) {
		super(view);
		dataKey = key;
	}
	
	@Override
	public boolean checkCondition(GestureEvent motion, TouchGesture gesture) {
		ScreenVector actionDown = (ScreenVector)gesture.getContext(dataKey);
		if(actionDown != null)
		{
			return (int)(actionDown.distance(motion.getPosition())) > 10;
		}

		return true;
	}

	private String dataKey;
}