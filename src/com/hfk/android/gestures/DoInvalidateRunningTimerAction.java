package com.hfk.android.gestures;

public class DoInvalidateRunningTimerAction implements IGestureAction {

	public DoInvalidateRunningTimerAction(TouchHandler handler) {
		//touchHandler = handler;
		//touchGesture = gesture;
	}

	@Override
	public void executeAction(GestureEvent motion, TouchGesture gesture) {
		if(gesture.contextExists("TIMER_INSTALLED"))
		{
			TouchHandler handler = (TouchHandler)gesture.getContext("TIMER_INSTALLED");
			handler.invalidateTimer();
			gesture.removeContext("TIMER_INSTALLED");
		}
	}
	
	//private TouchHandler touchHandler;
	//private TouchGesture touchGesture;
}