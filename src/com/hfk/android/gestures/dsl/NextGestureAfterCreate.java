package com.hfk.android.gestures.dsl;

import com.hfk.android.gestures.TouchEvent;
import com.hfk.android.gestures.TouchGesture;

public class NextGestureAfterCreate {
	
	public NextGestureAfterCreate(TouchGesture gstr)
	{
		gesture = gstr;
	}
	
	public ActionAfterGestureOrConditional<NextGestureAfterTouchDown> TouchDown()
	{
	    TouchEvent downEvent = new TouchEvent();
	    downEvent.event = TouchEvent.TOUCH_DOWN;
	    
	    gesture.addEvent(downEvent);
		
		ActionAfterGestureOrConditional<NextGestureAfterTouchDown> touchDown = new ActionAfterGestureOrConditional<NextGestureAfterTouchDown>(NextGestureAfterTouchDown.class, gesture, downEvent);
		return touchDown;
	}
	
	TouchGesture gesture;
}
