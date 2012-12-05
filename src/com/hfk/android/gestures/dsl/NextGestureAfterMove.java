package com.hfk.android.gestures.dsl;

import com.hfk.android.gestures.TouchEvent;
import com.hfk.android.gestures.TouchGesture;

public class NextGestureAfterMove {
	
	public NextGestureAfterMove(TouchGesture gstr)
	{
		gesture = gstr;
	}
	
	public ActionAfterGestureOrConditional<NextGestureAfterTouchUp> TouchUp()
	{
	    TouchEvent upEvent = new TouchEvent();
	    upEvent.event = TouchEvent.TOUCH_UP;
	    
	    gesture.addEvent(upEvent);
		
		ActionAfterGestureOrConditional<NextGestureAfterTouchUp> move = new ActionAfterGestureOrConditional<NextGestureAfterTouchUp>(NextGestureAfterTouchUp.class, gesture, upEvent);
		return move;
	}
	
	TouchGesture gesture;

}
