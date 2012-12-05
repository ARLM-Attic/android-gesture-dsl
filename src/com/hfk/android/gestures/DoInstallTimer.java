package com.hfk.android.gestures;

public class DoInstallTimer implements IGestureAction {

//	public DoInstallTimer(TouchHandler handler, TouchGesture gesture, IGestureCondition condition, IGestureAction action, int timeOut) {
//		touchHandler = handler;
//		touchGesture = gesture;
//		timerCondition = condition;
//		timerAction = action;
//		timerTimeOut = timeOut;
//	}

	public DoInstallTimer(TouchHandler handler, IGestureAction action, int timeOut) {
		touchHandler = handler;
		//touchGesture = gesture;
//		timerCondition = new IGestureCondition()
//		{
//			public boolean checkCondition(GestureEvent motion, TouchGesture gesture)
//			{
//				return true;
//			}
//		};
		timerAction = action;
		timerTimeOut = timeOut;
	}

	@Override
	public void executeAction(GestureEvent motion, TouchGesture gesture) {
		gesture.addContext("TIMER_INSTALLED", touchHandler);
		touchHandler.installTimer(timerCondition, timerAction, timerTimeOut, gesture);
	}
	
	private TouchHandler touchHandler;
	//private TouchGesture touchGesture;

	private int timerTimeOut = 0;
	private IGestureAction timerAction = null;
	private IGestureCondition timerCondition = null;
}