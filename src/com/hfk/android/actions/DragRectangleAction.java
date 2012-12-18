package com.hfk.android.actions;

import android.graphics.Point;

import com.hfk.android.AndroidGestureDSLView;
import com.hfk.android.ScreenVector;
import com.hfk.android.gestures.GestureActionBase;
import com.hfk.android.gestures.GestureEvent;
import com.hfk.android.gestures.TouchGesture;

public class DragRectangleAction extends GestureActionBase<AndroidGestureDSLView> {

	public DragRectangleAction(AndroidGestureDSLView view) {
		super(view);
	}
	
	@Override
	public void executeAction(GestureEvent motion, TouchGesture gesture) {
		ScreenVector currentPosition = motion.getPosition(); 
		Point hitOffset = (Point)gesture.getContext(RegisterRectangleHitPointAction.RECTANGLE_CENTER_HITOFFSET);
		Point newRectangleCenter = new Point(currentPosition.x + hitOffset.x, currentPosition.y + hitOffset.y);
		getTouchedView().setRectangleCenter(newRectangleCenter);

		long timeStamp = motion.getTime(); 
		String message = "DragRectangleAction: " + ((Long)timeStamp).toString();
		//getTouchedView().drawMessage(message);
	}
	
	String message;
}
