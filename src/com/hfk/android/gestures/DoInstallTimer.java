package com.hfk.android.gestures;

public class DoInstallTimer implements IGestureAction {

	public DoInstallTimer(IGestureAction action, int timeOut) {
		timerAction = action;
		timerTimeOut = timeOut;
	}

	@Override
	public void executeAction(GestureEvent motion, TouchGesture gesture) {
		TouchHandler handler = (TouchHandler)gesture.getContext(TouchHandler.TouchHandler);
		handler.installTimer(timerAction, timerTimeOut, gesture);
	}

	private int timerTimeOut = 0;
	private IGestureAction timerAction = null;
}