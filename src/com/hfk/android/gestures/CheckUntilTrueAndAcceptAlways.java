package com.hfk.android.gestures;

public class CheckUntilTrueAndAcceptAlways implements IGestureCondition, IResetable {

	public CheckUntilTrueAndAcceptAlways(TouchGesture gesture, IGestureCondition condition) {
		checkCondition = condition;
		gesture.addResetable(this);
	}
	
	@Override
	public boolean checkCondition(GestureEvent motion, TouchGesture gesture) {
		if(!isTrue)
		{
			isTrue = checkCondition.checkCondition(motion, gesture);
		}
		return isTrue;
	}
	
	@Override
	public void reset() {
		isTrue = false;
	}
	
	IGestureCondition checkCondition;
	boolean isTrue = false;
}