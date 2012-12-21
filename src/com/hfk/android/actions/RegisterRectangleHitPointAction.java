package com.hfk.android.actions;

import android.graphics.Point;

import com.hfk.android.AndroidGestureDSLView;
import com.hfk.android.ScreenVector;
import com.hfk.android.gestures.GestureActionBase;
import com.hfk.android.gestures.GestureEvent;
import com.hfk.android.gestures.TouchGesture;
import com.hfk.android.gestures.TouchHandler;

public class RegisterRectangleHitPointAction extends GestureActionBase<AndroidGestureDSLView> {
	
	public static String RECTANGLE_CENTER_HITOFFSET = "RECTANGLE_CENTER_HITOFFSET";

	public RegisterRectangleHitPointAction(AndroidGestureDSLView view) {
		super(view);
	}
	
	@Override
	public void executeAction(GestureEvent motion, TouchGesture gesture) {
		Point rectangleCenter = getTouchedView().getRectangleCenter();
		ScreenVector touchDownPoint = motion.getPosition();//(ScreenVector)gesture.getContext(TouchHandler.ActionDownPos);
		
		Point hitOffset = new Point(rectangleCenter.x - touchDownPoint.x, rectangleCenter.y - touchDownPoint.y);
		
		gesture.addContext(RECTANGLE_CENTER_HITOFFSET, hitOffset);
	}
	
	String message;
}
