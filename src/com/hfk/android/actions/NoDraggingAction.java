package com.hfk.android.actions;

import android.graphics.Point;

import com.hfk.android.AndroidGestureDSLView;
import com.hfk.android.ScreenVector;
import com.hfk.android.gestures.GestureActionBase;
import com.hfk.android.gestures.GestureEvent;
import com.hfk.android.gestures.TouchGesture;

public class NoDraggingAction extends GestureActionBase<AndroidGestureDSLView> {

	public NoDraggingAction(AndroidGestureDSLView view) {
		super(view);
	}
	
	@Override
	public void executeAction(GestureEvent motion, TouchGesture gesture) {
		long timeStamp = motion.getTime(); 
		String message = "NoDraggingAction: " + ((Long)timeStamp).toString();
		//getTouchedView().drawMessage(message);
	}
	
	String message;
}