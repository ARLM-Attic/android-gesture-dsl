package com.hfk.android.gestures;

import com.hfk.android.AndroidGestureDSLView;

import android.view.MotionEvent;
import android.view.View;

public class DoInvalidateGestureGestureAction implements IGestureAction {
	@Override
	public void executeAction(GestureEvent motion, TouchGesture gesture) {
		gesture.invalidate();
	}
}
