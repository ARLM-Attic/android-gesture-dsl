package com.hfk.android.gestures;

import android.view.View;

import com.hfk.android.ScreenVector;

public class CheckWithinMilliSecondsOfTimingId extends GestureConditionBase<View> {

	public CheckWithinMilliSecondsOfTimingId(View view, String key) {
		super(view);
		dataKey = key;
	}
	
	@Override
	public boolean checkCondition(GestureEvent motion, TouchGesture gesture) {
		Long actionOnTime = (Long)gesture.getContext(dataKey);
		if(actionOnTime != null)
		{
			return (long)(motion.getTime() - actionOnTime.longValue()) < 500;
		}

		return true;
	}

	private String dataKey;
}