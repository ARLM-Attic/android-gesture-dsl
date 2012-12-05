package com.hfk.android.gestures;

import android.view.MotionEvent;

public interface IGestureAction {
	void executeAction(GestureEvent motion, TouchGesture gesture);
}
