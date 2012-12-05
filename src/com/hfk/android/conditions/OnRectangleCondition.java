package com.hfk.android.conditions;

import java.util.List;

import com.hfk.android.AndroidGestureDSLView;
import com.hfk.android.gestures.GestureConditionBase;
import com.hfk.android.gestures.GestureEvent;
import com.hfk.android.gestures.TouchGesture;

public class OnRectangleCondition extends GestureConditionBase<AndroidGestureDSLView> {

	public OnRectangleCondition(AndroidGestureDSLView view) {
		super(view);
	}
	
	@Override
	public boolean checkCondition(GestureEvent motion, TouchGesture gesture) {
		return getTouchedView().IsOnRectangle(motion.getPosition());
	}

}
