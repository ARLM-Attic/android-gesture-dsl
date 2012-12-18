package com.hfk.android.gestures.dsl;

import android.view.View;

import com.hfk.android.gestures.CheckWithinDistanceOfRegisteredPointCondition;
import com.hfk.android.gestures.CheckWithinMilliSecondsOfTimingIdCondition;
import com.hfk.android.gestures.IGestureCondition;
import com.hfk.android.gestures.TouchHandler;

public class TimeRangeStartSelector {
	public TimeRangeStartSelector(int distanceRange, View parentView)
	{
		range = distanceRange;
		view = parentView;
	}
	
//	public IGestureCondition fromTouchDown()
//	{
//		return new CheckWithinMilliSecondsOfTimingIdCondition(view, range, TouchHandler.ActionDownTime);
//	}
	
	public IGestureCondition fromTouchDown(int index)
	{
		return new CheckWithinMilliSecondsOfTimingIdCondition(view, range, TouchHandler.getEventId(TouchHandler.ActionDownTime, index));
	}
	
	public IGestureCondition fromMove()
	{
		return new CheckWithinMilliSecondsOfTimingIdCondition(view, range, TouchHandler.ActionMoveTime);
	}
	
	public IGestureCondition fromTouchUp(int index)
	{
		return new CheckWithinMilliSecondsOfTimingIdCondition(view, range, TouchHandler.getEventId(TouchHandler.ActionUpTime, index));
	}
	
	private int range;
	private View view;
}
