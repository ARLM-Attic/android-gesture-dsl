package com.hfk.android.gestures;

import android.view.MotionEvent;

public interface IGestureCondition {
	boolean checkCondition(GestureEvent motion, TouchGesture gesture);
}
