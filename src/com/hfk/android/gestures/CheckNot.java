package com.hfk.android.gestures;

import com.hfk.android.ScreenVector;

public class CheckNot implements IGestureCondition {

	public CheckNot(IGestureCondition condition) {
		notCondition = condition;
	}
	
	@Override
	public boolean checkCondition(GestureEvent motion, TouchGesture gesture) {
		return !notCondition.checkCondition(motion, gesture);
	}
	
	IGestureCondition notCondition;
}
