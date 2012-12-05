package com.hfk.android.gestures.dsl;

import com.hfk.android.gestures.TouchEvent;
import com.hfk.android.gestures.TouchGesture;

public class NextGestureAfterTouchUp {
	
	public NextGestureAfterTouchUp(TouchGesture gstr)
	{
		gesture = gstr;
	}
	
	public ActionAfterGestureOrConditional<NextGestureAfterMove> Move()
	{
	    TouchEvent moveEvent = new TouchEvent();
	    moveEvent.event = TouchEvent.TOUCH_MOVE;
	    
	    gesture.addEvent(moveEvent);
		
		ActionAfterGestureOrConditional<NextGestureAfterMove> move = new ActionAfterGestureOrConditional<NextGestureAfterMove>(NextGestureAfterMove.class, gesture, moveEvent);
		return move;
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
