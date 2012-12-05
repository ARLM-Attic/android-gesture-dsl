package com.hfk.android.gestures;

import android.view.MotionEvent;
import android.view.View;

public class DoInvalidateGestureGestureAction implements IGestureAction {

	public DoInvalidateGestureGestureAction() {
		//touchGesture = gesture;
	}

	@Override
	public void executeAction(GestureEvent motion, TouchGesture gesture) {
		gesture.invalidate();
	}
	
	//private TouchGesture touchGesture;
}
