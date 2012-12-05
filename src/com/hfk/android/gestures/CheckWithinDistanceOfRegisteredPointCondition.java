package com.hfk.android.gestures;

import android.view.View;

import com.hfk.android.ScreenVector;

public class CheckWithinDistanceOfRegisteredPointCondition extends GestureConditionBase<View> {

	public CheckWithinDistanceOfRegisteredPointCondition(View view, int rangeValue, String key) {
		super(view);
		
		range = rangeValue;
		dataKey = key;
	}
	
	@Override
	public boolean checkCondition(GestureEvent motion, TouchGesture gesture) {
		ScreenVector actionDown = (ScreenVector)gesture.getContext(dataKey);
		if(actionDown != null)
		{
			return (int)(actionDown.distance(motion.getPosition())) <= range;
		}

		return true;
	}

	private int range;
	private String dataKey;
}