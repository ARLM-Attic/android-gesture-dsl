package com.hfk.android.gestures;

public class DoInvalidateRunningTimerAction implements IGestureAction {

	public DoInvalidateRunningTimerAction() {
	}

	@Override
	public void executeAction(GestureEvent motion, TouchGesture gesture) {
		if(gesture.contextExists(TouchHandler.TouchHandlerId))
		{
			TouchHandler handler = (TouchHandler)gesture.getContext(TouchHandler.TouchHandlerId);
			handler.invalidateTimer();
		}
	}
}