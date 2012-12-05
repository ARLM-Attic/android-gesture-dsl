package com.hfk.android.gestures;

import android.view.View;

import com.hfk.android.ScreenVector;

public class CheckWithinMilliSecondsOfTimingIdCondition extends GestureConditionBase<View> {

	public CheckWithinMilliSecondsOfTimingIdCondition(View view, int rangeValue, String key) {
		super(view);
		
		range = rangeValue;
		dataKey = key;
	}
	
	@Override
	public boolean checkCondition(GestureEvent motion, TouchGesture gesture) {
		Long actionOnTime = (Long)gesture.getContext(dataKey);
		if(actionOnTime != null)
		{
			return (long)(motion.getTime() - actionOnTime.longValue()) <= range;
		}

		return true;
	}

	private int range;
	private String dataKey;
}